package kz.auto_life.taxservice.services.impl;

import kz.auto_life.taxservice.exceptions.GrnzExistsException;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.payload.VehicleRequest;
import kz.auto_life.taxservice.repositories.VehicleRepository;
import kz.auto_life.taxservice.services.TaxService;
import kz.auto_life.taxservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final TaxService taxService;

    private boolean grnzExists(String grnz) {
        return vehicleRepository.findByGrnz(grnz) != null;
    }

    @Override
    public Vehicle save(VehicleRequest vehicle) {
        if (grnzExists(vehicle.getGrnz())) {
            throw new GrnzExistsException(String.format("Vehicle with grnz: '%s' already exists", vehicle.getGrnz()));
        } else {
            Vehicle v = new Vehicle();
            v.setUserIin(vehicle.getIin());
            v.setGrnz(vehicle.getGrnz());
            v.setType(vehicle.getVehicleType());
            v.setKey(vehicle.getAttributes().get(0).getKey());
            v.setValue(vehicle.getAttributes().get(0).getValue());
            return vehicleRepository.save(v);
        }
    }

    @Override
    public List<Vehicle> getAll() {
        log.info("Fetching all vehicles from the database");
        return vehicleRepository.findAll();
    }
}
