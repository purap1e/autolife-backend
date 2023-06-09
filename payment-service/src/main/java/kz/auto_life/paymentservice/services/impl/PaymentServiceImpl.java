package kz.auto_life.paymentservice.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.auto_life.paymentservice.models.Accommodation;
import kz.auto_life.paymentservice.payload.PurchaseAttributes;
import kz.auto_life.paymentservice.payload.WithdrawRequest;
import kz.auto_life.paymentservice.repositories.ServiceRepository;
import kz.auto_life.paymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ServiceRepository serviceRepository;
    private final ServletContext servletContext;
    private final String postUrlForWithdraw = "http://176.9.24.125:12565/api/cards/withdraw";
    private final String postUrlForPayTaxes = "http://176.9.24.125:12565/api/taxes/pay";
    private final String postUrlForPayFines = "http://176.9.24.125:12565/api/fines/pay";
    private final String postUrlForPurchaseItems = "http://176.9.24.125:12565/api/shop/items/purchase";

    public String getToken() {
        return String.valueOf(servletContext.getAttribute("token"));
    }

    @Override
    public List<?> pay(WithdrawRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            String json = new ObjectMapper().writeValueAsString(request);
            HttpEntity<String> entity = new HttpEntity<>(json, headers);
            ResponseEntity<String> res = restTemplate.exchange(postUrlForWithdraw, HttpMethod.POST, entity, String.class);
            if (Objects.equals(res.getBody(), "success")) {
                Accommodation accommodation = serviceRepository.findById(request.getServiceId()).orElseThrow(() -> new RuntimeException("Service not found"));

                switch (accommodation.getName()) {
                    case "fines":
                        return restTemplate.exchange(postUrlForPayFines, HttpMethod.POST, entity, new ParameterizedTypeReference<List<?>>() {
                        }).getBody();
                    case "taxes":
                        return restTemplate.exchange(postUrlForPayTaxes, HttpMethod.POST, entity, new ParameterizedTypeReference<List<?>>() {
                        }).getBody();
                    case "shop":
                        List<PurchaseAttributes> attributes = new ArrayList<>();
                        request.getAttributes()
                                .forEach(x -> {
                                    attributes.add(new PurchaseAttributes(x.getId(), x.getCount()));
                                });
                        String jsonShop = new ObjectMapper().writeValueAsString(attributes);
                        HttpEntity<String> entityShop = new HttpEntity<>(jsonShop, headers);
                        return restTemplate.exchange(postUrlForPurchaseItems, HttpMethod.POST, entityShop, new ParameterizedTypeReference<List<?>>(){
                        }).getBody();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
