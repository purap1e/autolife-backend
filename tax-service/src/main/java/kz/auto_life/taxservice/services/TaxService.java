package kz.auto_life.taxservice.services;

import kz.auto_life.taxservice.models.Tax;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.payload.WithdrawRequest;

import java.util.List;

public interface TaxService {
    void process();
    List<TaxResponse> getAllForUser(String iin, Boolean paid);

    List<TaxResponse> updateTaxes(WithdrawRequest request);
}
