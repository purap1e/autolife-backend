package kz.auto_life.finecarservice.mappers;

import kz.auto_life.finecarservice.models.Fine;
import kz.auto_life.finecarservice.payload.FineResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FineResponseMapper implements Function<Fine, FineResponse> {
    public static final int MCI = 3093;

    @Override
    public FineResponse apply(Fine fine) {
        return new FineResponse(
                fine.getId(),
                fine.getUserIin(),
                fine.getDescription(),
                fine.getGrnz(),
                fine.getVehicleType(),
                fine.getAmountMci() * MCI,
                fine.getPaid());
    }
}
