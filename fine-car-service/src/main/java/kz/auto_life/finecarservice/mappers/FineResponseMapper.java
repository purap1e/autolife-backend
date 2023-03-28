package kz.auto_life.finecarservice.mappers;

import kz.auto_life.finecarservice.models.Fine;
import kz.auto_life.finecarservice.models.Vehicle;
import kz.auto_life.finecarservice.payload.FineResponse;
import kz.auto_life.finecarservice.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FineResponseMapper implements Function<Fine, FineResponse> {
    public static final int MCI = 3093;
    private final VehicleRepository vehicleRepository;

    @Override
    public FineResponse apply(Fine fine) {

        Vehicle vehicle = vehicleRepository.findById(fine.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found"));

        return new FineResponse(
                fine.getId(),
                vehicle.getUserIin(),
                fine.getDescription(),
                vehicle.getGrnz(),
                vehicle.getType(),
                BigDecimal.valueOf((long) fine.getAmountMci() * MCI),
                fine.getPaid());
    }
}
