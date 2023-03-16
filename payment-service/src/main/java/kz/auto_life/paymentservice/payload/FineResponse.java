package kz.auto_life.paymentservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineResponse {
    private Long id;
    private String iin;
    private String description;
    private String grnz;
    private String type;
    private int amount;
    private Boolean paid;
}
