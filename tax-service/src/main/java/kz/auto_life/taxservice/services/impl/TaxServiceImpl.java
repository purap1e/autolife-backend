package kz.auto_life.taxservice.services.impl;

import kz.auto_life.taxservice.mappers.TaxMapper;
import kz.auto_life.taxservice.models.Tax;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.payload.WithdrawRequest;
import kz.auto_life.taxservice.repositories.TaxRepository;
import kz.auto_life.taxservice.repositories.VehicleRepository;
import kz.auto_life.taxservice.services.TaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    public static final double MCI = 3093;

    private final TaxRepository taxRepository;
    private final VehicleRepository vehicleRepository;
    private final TaxMapper taxMapper;
    private static final String CURRENCY_KZ = "KZT";
    private final double defaultEngineCapacity = 1500;

    private final double[][] valuesOfEngineCapacity = {{1100, 1}, {1500, 2}, {2000, 3}, {2500, 6}, {3000, 9}, {4000, 15}, {5000, 117}, {0, 200}};
    private final double[][] valuesOfLoadCapacity = {{1, 3}, {1.5, 5}, {5, 7}, {0, 9}};
    private final double[][] seatsOfBus = {{12, 9}, {25, 14}, {0, 20}};


    public BigDecimal calculateAmount(double[][] array, String vehicleValue) {
        double value = Double.parseDouble(vehicleValue);
        BigDecimal res = new BigDecimal(0);
        double exceed = 0;
        if (value > defaultEngineCapacity) {
            exceed = (value - defaultEngineCapacity) * 7;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (value <= array[i][0]) {
                res = BigDecimal.valueOf(array[i][1] * MCI + exceed);
                break;
            }
        }
        if (!res.equals(new BigDecimal(0))) {
            return res;
        }
        return BigDecimal.valueOf(MCI * array[array.length - 1][1] + exceed);
    }

    public BigDecimal getAmount(String vehicleType, String vehicleValue) {
        return switch (vehicleType) {
            case "CAR" -> calculateAmount(valuesOfEngineCapacity, vehicleValue);
            case "BUS" -> calculateAmount(seatsOfBus, vehicleValue);
            case "FREIGHT" -> calculateAmount(valuesOfLoadCapacity, vehicleValue);
            default -> BigDecimal.valueOf(0);
        };
    }

    @Override
    public void process() {
        log.info("Saving taxes to the database");
        List<Vehicle> vehicles = vehicleRepository.findAll();

        taxRepository.deleteAll();

        taxRepository.saveAll(
                vehicles
                        .stream()
                        .map(v -> {
                            Tax tax = new Tax();
                            tax.setUserIin(v.getUserIin());
                            tax.setGrnz(v.getGrnz());
                            tax.setType(v.getType());
                            tax.setAmount(getAmount(v.getType(), v.getValue()));
                            tax.setCurrency(CURRENCY_KZ);
                            return tax;
                        }).toList()
        );
    }

    @Override
    public List<TaxResponse> getAllForUser(String iin, Boolean paid) {
        return taxRepository.findAllByUserIinAndPaid(iin, paid)
                .stream()
                .map(taxMapper)
                .toList();
    }

    public Tax getById(UUID id) {
        Tax tax = taxRepository.findById(id).orElseThrow(() -> new RuntimeException("Tax not found"));
        tax.setPaid(true);
        return taxRepository.save(tax);
    }

    @Override
    public List<TaxResponse> updateTaxes(WithdrawRequest request) {
        List<Tax> taxes = new ArrayList<>();
        request.getAttributes()
                .forEach(x -> taxes.add(getById(x.getId())));
        return taxes.stream().map(taxMapper).toList();
    }
}
