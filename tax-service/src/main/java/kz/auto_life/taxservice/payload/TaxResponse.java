package kz.auto_life.taxservice.payload;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxResponse {
    private Long id;
    private String iin;
    private String grnz;
    private String vehicleType;
    private BigDecimal amount;
    private String currency;
    private Boolean paid;
}
