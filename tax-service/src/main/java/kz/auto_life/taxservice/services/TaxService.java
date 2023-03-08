package kz.auto_life.taxservice.services;

import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Tax;

import java.util.List;

public interface TaxService {
    void process();
    List<Tax> getAllByIinAndStatus(String iin, int status);

    List<Tax> payTaxes(List<Long> ids);
}
