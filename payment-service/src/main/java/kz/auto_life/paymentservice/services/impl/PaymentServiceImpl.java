package kz.auto_life.paymentservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.auto_life.paymentservice.filters.CustomAuthorizationFilter;
import kz.auto_life.paymentservice.models.WithdrawRequest;
import kz.auto_life.paymentservice.payload.TaxResponse;
import kz.auto_life.paymentservice.services.PaymentService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    private String postUrlForWithdraw = "http://localhost:12565/api/cards/withdraw";
    private String postUrlForPay = "http://localhost:12565//api/taxes/pay";

    @Override
    public List<TaxResponse> payTaxes(WithdrawRequest request) {
        List<TaxResponse> taxes = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + CustomAuthorizationFilter.token1);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = new ObjectMapper().writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> res = restTemplate.exchange(postUrlForWithdraw, HttpMethod.POST, entity, String.class);
            if (Objects.equals(res.getBody(), "success")) {
                taxes =  restTemplate.exchange(postUrlForPay, HttpMethod.POST, entity, new ParameterizedTypeReference<List<TaxResponse>>() {}).getBody();
            }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        return taxes;
    }
}
