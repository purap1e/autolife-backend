package kz.auto_life.paymentservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FineResponse {
    private UUID id;
    private String iin;
    private String description;
    private String grnz;
    private String type;
    private int amount;
    private Boolean paid;
}
