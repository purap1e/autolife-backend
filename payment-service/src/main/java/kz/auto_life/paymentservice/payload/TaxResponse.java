package kz.auto_life.paymentservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class TaxResponse {
    private UUID id;
    private String iin;
    private String grnz;
    private String vehicleType;
    private BigDecimal amount;
    private Boolean paid;
}
