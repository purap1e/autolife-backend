package kz.auto_life.taxservice.payload;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class TaxRequest {
    private List<UUID> ids;
}
