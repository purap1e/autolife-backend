package kz.auto_life.taxservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.auto_life.taxservice.filters.CustomAuthorizationFilter;
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
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {
    public static final double MCI = 3093;

    private final TaxRepository taxRepository;
    private final VehicleRepository vehicleRepository;
    private final TaxMapper taxMapper;

    private String postUrlForWithdraw = "http://localhost:12565/api/cards/withdraw";

    public BigDecimal getAmount(String vehicleType, String vehicleValue) {
        switch (vehicleType) {
            case "CAR":
                int engineCapacity = Integer.parseInt(vehicleValue);
                if (engineCapacity <= 1100) {
                    return new BigDecimal(MCI);
                } else if (engineCapacity <= 1500) {
                    return new BigDecimal(MCI * 2);
                } else if (engineCapacity <= 2000) {
                    return new BigDecimal(MCI * 3 + (engineCapacity - 1500) * 7);
                } else if (engineCapacity <= 2500) {
                    return new BigDecimal(MCI * 6 + (engineCapacity - 1500) * 7);
                } else if (engineCapacity <= 3000) {
                    return new BigDecimal(MCI * 9 + (engineCapacity - 1500) * 7);
                } else if (engineCapacity <= 4000) {
                    return new BigDecimal(MCI * 15 + (engineCapacity - 1500) * 7);
                } else if (engineCapacity <= 5000) {
                    return new BigDecimal(MCI * 117 + (engineCapacity - 1500) * 7);
                } else {
                    return new BigDecimal(MCI * 200 + (engineCapacity - 1500) * 7);
                }
            case "BUS":
                int seats = Integer.parseInt(vehicleValue);
                if (seats <= 12) {
                    return new BigDecimal(MCI * 9);
                } else if (seats <= 25) {
                    return new BigDecimal(MCI * 14);
                } else {
                    return new BigDecimal(MCI * 20);
                }
            case "FREIGHT":
                double loadCapacity = Double.parseDouble(vehicleValue);
                if (loadCapacity <= 1) {
                    return new BigDecimal(MCI * 3);
                } else if (loadCapacity <= 1.5) {
                    return new BigDecimal(MCI * 5);
                } else if (loadCapacity <= 5) {
                    return new BigDecimal(MCI * 7);
                } else {
                    return new BigDecimal(MCI * 9);
                }
        }
        return BigDecimal.valueOf(0);
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
    public List<TaxResponse> getAllForUser(String iin, Boolean paid) {
        return taxRepository.findAllByUserIinAndPaid(iin, paid)
                .stream()
                .map(taxMapper)
                .toList();
    }

    public Tax getById(Long id) {
        Tax tax = taxRepository.findById(id).orElseThrow(() -> new RuntimeException("Tax not found"));
        tax.setPaid(true);
        return taxRepository.save(tax);
    }

    @Override
    public List<TaxResponse> payTaxes(WithdrawRequest request) {
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
        return taxes.stream().map(taxMapper).toList();
    }
}
