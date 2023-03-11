package kz.auto_life.paymentservice.payload;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TaxResponse {
    private Long id;
    private String iin;
    private String grnz;
    private String vehicleType;
    private BigDecimal amount;
    private Boolean paid;
}
