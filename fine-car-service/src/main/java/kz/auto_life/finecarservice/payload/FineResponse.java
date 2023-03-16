package kz.auto_life.finecarservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FineResponse {
    private Long id;
    private String iin;
    private String description;
    private String grnz;
    private String type;
    private int amount;
    private Boolean paid;
}
