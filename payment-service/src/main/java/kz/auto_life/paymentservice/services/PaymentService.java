package kz.auto_life.paymentservice.services;

import kz.auto_life.paymentservice.models.WithdrawRequest;
import kz.auto_life.paymentservice.payload.TaxResponse;

import java.util.List;

public interface PaymentService {
    List<TaxResponse> payTaxes(WithdrawRequest request);
}
