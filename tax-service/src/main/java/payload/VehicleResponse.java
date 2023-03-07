package payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VehicleResponse {
    private String userIin;
    private String grnz;
    private String vehicleType;
    private List<VehicleAttributes> attributes;
}
