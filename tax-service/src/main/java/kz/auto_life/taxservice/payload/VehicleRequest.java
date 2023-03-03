package kz.auto_life.taxservice.payload;

import lombok.Getter;

import java.util.List;

@Getter
public class VehicleRequest {
    private String iin;
    private String grnz;
    private String vehicleType;
    private List<VehicleAttributes> attributes;
}
