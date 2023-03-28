package kz.auto_life.taxservice.mappers;

import kz.auto_life.taxservice.models.Tax;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TaxMapper implements Function<Tax, TaxResponse> {

    private final VehicleRepository vehicleRepository;

    @Override
    public TaxResponse apply(Tax tax) {
        Vehicle vehicle = vehicleRepository.findById(tax.getVehicleId()).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return new TaxResponse(
                tax.getId(),
                vehicle.getUserIin(),
                vehicle.getGrnz(),
                vehicle.getType(),
                tax.getAmount(),
                tax.getCurrency(),
                tax.getPaid());
    }
}
