package kz.auto_life.paymentservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.auto_life.paymentservice.filters.CustomAuthorizationFilter;
import kz.auto_life.paymentservice.payload.WithdrawRequest;
import kz.auto_life.paymentservice.payload.FineResponse;
import kz.auto_life.paymentservice.payload.TaxResponse;
import kz.auto_life.paymentservice.services.PaymentService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final String postUrlForWithdraw = "http://localhost:12565/api/cards/withdraw";
    private final String postUrlForPayTaxes = "http://localhost:12565/api/taxes/pay";
    private final String postUrlForPayFines = "http://localhost:12565/api/fines/pay";

    @Override
    public List<TaxResponse> payTaxes(WithdrawRequest request) {
        List<TaxResponse> taxes = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + CustomAuthorizationFilter.token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = new ObjectMapper().writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> res = restTemplate.exchange(postUrlForWithdraw, HttpMethod.POST, entity, String.class);
            if (Objects.equals(res.getBody(), "success")) {
                taxes = restTemplate.exchange(postUrlForPayTaxes, HttpMethod.POST, entity, new ParameterizedTypeReference<List<TaxResponse>>() {
                }).getBody();
            }
            return taxes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<FineResponse> payFines(WithdrawRequest request) {
        List<FineResponse> fines = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + CustomAuthorizationFilter.token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = new ObjectMapper().writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> res = restTemplate.exchange(postUrlForWithdraw, HttpMethod.POST, entity, String.class);
            if (Objects.equals(res.getBody(), "success")) {
                fines = restTemplate.exchange(postUrlForPayFines, HttpMethod.POST, entity, new ParameterizedTypeReference<List<FineResponse>>() {
                }).getBody();
            }
            return fines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
