package kz.auto_life.taxservice.services;

import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Bus;
import kz.auto_life.taxservice.models.childs.Car;
import kz.auto_life.taxservice.models.childs.Freight;

public interface VehicleService {
    Vehicle save(Vehicle vehicle);
    Bus saveBus(Bus bus);
    Car saveCar(Car car);
    Freight saveFreight(Freight freight);
}
