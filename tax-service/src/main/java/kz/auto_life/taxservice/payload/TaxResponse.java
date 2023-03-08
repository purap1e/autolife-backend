package kz.auto_life.taxservice.payload;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TaxResponse {
    private Long id;
    private String iin;
    private String grnz;
    private String vehicleType;
    private int amount;
    private int status;
}
