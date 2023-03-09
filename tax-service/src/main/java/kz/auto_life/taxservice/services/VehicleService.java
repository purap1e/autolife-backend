package kz.auto_life.taxservice.services;

import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.payload.VehicleRequest;

import java.util.List;

public interface VehicleService {
    Vehicle save(VehicleRequest vehicle);
    List<Vehicle> getAll();
}
