package kz.auto_life.stoservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class StoDTO {
    private UUID uuid;
    private String title;
    private String phone;
    private String description;
    private String location;
    private int countOfComments;
    private double grade;
    private List<String> imageUrls;
}
