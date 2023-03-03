package kz.auto_life.taxservice.services;

import kz.auto_life.taxservice.payload.VehicleRequest;

public interface CheckTypeService {
    Object createObject(VehicleRequest vehicleRequest);
}
