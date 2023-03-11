package kz.auto_life.taxservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.auto_life.taxservice.filters.CustomAuthorizationFilter;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Tax;
import kz.auto_life.taxservice.payload.WithdrawRequest;
import kz.auto_life.taxservice.repositories.TaxRepository;
import kz.auto_life.taxservice.repositories.VehicleRepository;
import kz.auto_life.taxservice.services.TaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    public static final int MCI = 3093;

    private final TaxRepository taxRepository;
    private final VehicleRepository vehicleRepository;

    private String postUrlForWithdraw = "http://localhost:12565/api/cards/withdraw";

    public int getAmount(String vehicleType, String vehicleValue) {
        switch (vehicleType) {
            case "CAR":
                int engineCapacity = Integer.parseInt(vehicleValue);
                if (engineCapacity <= 1100) {
                    return MCI;
                } else if (engineCapacity <= 1500) {
                    return MCI * 2;
                } else if (engineCapacity <= 2000) {
                    return MCI * 3 + (engineCapacity - 1500) * 7;
                } else if (engineCapacity <= 2500) {
                    return MCI * 6 + (engineCapacity - 1500) * 7;
                } else if (engineCapacity <= 3000) {
                    return MCI * 9 + (engineCapacity - 1500) * 7;
                } else if (engineCapacity <= 4000) {
                    return MCI * 15 + (engineCapacity - 1500) * 7;
                } else if (engineCapacity <= 5000) {
                    return MCI * 117 + (engineCapacity - 1500) * 7;
                } else {
                    return MCI * 200 + (engineCapacity - 1500) * 7;
                }
            case "BUS":
                int seats = Integer.parseInt(vehicleValue);
                if (seats <= 12) {
                    return MCI * 9;
                } else if (seats <= 25) {
                    return MCI * 14;
                } else {
                    return MCI * 20;
                }
            case "FREIGHT":
                double loadCapacity = Double.parseDouble(vehicleValue);
                if (loadCapacity <= 1) {
                    return MCI * 3;
                } else if (loadCapacity <= 1.5) {
                    return MCI * 5;
                } else if (loadCapacity <= 5) {
                    return MCI * 7;
                } else {
                    return MCI * 9;
                }
        }
        return 0;
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
                            return tax;
                        }).toList()
        );
    }

    @Override
    public List<Tax> getAllByIinAndStatus(String iin, int status) {
        return taxRepository.findAllByUserIinAndStatus(iin, status);
    }

    public Tax getById(Long id) {
        Tax tax = taxRepository.findById(id).orElseThrow(() -> new RuntimeException("Tax not found"));
        tax.setStatus(2);
        return taxRepository.save(tax);
    }

    @Override
    public List<Tax> payTaxes(WithdrawRequest request) {
        List<Tax> taxes = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + CustomAuthorizationFilter.token1);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = new ObjectMapper().writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> res = restTemplate.exchange(postUrlForWithdraw, HttpMethod.POST, entity, String.class);
            System.out.println(res);

            if (Objects.equals(res.getBody(), "success")) {
                request.getAttributes()
                        .forEach(x -> taxes.add(getById(x.getId())));
            } else {
                throw new RuntimeException("error");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return taxes;
    }
}
