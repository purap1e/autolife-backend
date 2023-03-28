package kz.auto_life.finecarservice.payload;

import lombok.Getter;

import java.util.UUID;

@Getter
public class FineRequest {
    private UUID vehicleId;
    private String description;
    private int amountOfMci;
}
