package kz.auto_life.taxservice.mappers;

import kz.auto_life.taxservice.models.Tax;
import kz.auto_life.taxservice.payload.TaxResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
public class TaxMapper implements Function<Tax, TaxResponse> {
    @Override
    public TaxResponse apply(Tax tax) {
        return new TaxResponse(
                tax.getId(),
                tax.getUserIin(),
                tax.getGrnz(),
                tax.getType(),
                tax.getAmount(),
                tax.getCurrency(),
                tax.getPaid());
    }
}
