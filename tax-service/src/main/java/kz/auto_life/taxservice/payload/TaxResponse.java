package kz.auto_life.taxservice.payload;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxResponse {
    private String iin;
    private String grnz;
    private String vehicleType;
    private int amount;
}
