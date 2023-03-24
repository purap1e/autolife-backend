package kz.auto_life.paymentservice.services;

import kz.auto_life.paymentservice.payload.WithdrawRequest;
import kz.auto_life.paymentservice.payload.FineResponse;
import kz.auto_life.paymentservice.payload.TaxResponse;

import java.util.List;

public interface PaymentService {
    List<?> pay(WithdrawRequest request);
}
