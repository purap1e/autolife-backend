package kz.auto_life.taxservice.payload;

import lombok.Getter;

import java.util.List;

@Getter
public class TaxRequest {
    private List<Long> ids;
}
