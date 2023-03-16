package kz.auto_life.finecarservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FineResponse {
    private UUID id;
    private String iin;
    private String description;
    private String grnz;
    private String type;
    private BigDecimal amount;
    private Boolean paid;
}
