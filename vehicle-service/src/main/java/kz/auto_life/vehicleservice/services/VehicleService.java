package kz.auto_life.vehicleservice.services;

import kz.auto_life.vehicleservice.models.childs.Bus;
import kz.auto_life.vehicleservice.models.childs.Car;
import kz.auto_life.vehicleservice.models.childs.Freight;

public interface VehicleService {
    Bus saveBus(Bus bus);
    Car saveCar(Car car);
    Freight saveFreight(Freight freight);
}
