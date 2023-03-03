package kz.auto_life.taxservice.services;

import kz.auto_life.taxservice.models.childs.Bus;
import kz.auto_life.taxservice.models.childs.Car;
import kz.auto_life.taxservice.models.childs.Freight;
import kz.auto_life.taxservice.payload.TaxResponse;

import java.util.List;

public interface VehicleService {
    Bus saveBus(Bus bus);
    Car saveCar(Car car);
    Freight saveFreight(Freight freight);

    List<TaxResponse> getAll();
}
