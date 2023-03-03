package kz.auto_life.taxservice.services.impl;

import kz.auto_life.taxservice.enums.VehicleTypes;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Bus;
import kz.auto_life.taxservice.models.childs.Car;
import kz.auto_life.taxservice.models.childs.Freight;
import kz.auto_life.taxservice.services.CheckTypeService;
import kz.auto_life.taxservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kz.auto_life.taxservice.payload.VehicleRequest;

@Service
@RequiredArgsConstructor
public class CheckTypeServiceImpl implements CheckTypeService {

    private final VehicleService vehicleService;
    @Override
    public Vehicle createObject(VehicleRequest vehicleRequest) {
        String vehicleType = vehicleRequest.getVehicleType();
        switch (vehicleType) {
            case "CAR":
                Car car = new Car();
                car.setUserIin(vehicleRequest.getIin());
                car.setGrnz(vehicleRequest.getGrnz());
                car.setType(String.valueOf(VehicleTypes.CAR));
                car.setEngineCapacity(Integer.parseInt(vehicleRequest.getAttributes().get(0).getValue()));
                return vehicleService.saveCar(car);
            case "BUS":
                Bus bus = new Bus();
                bus.setUserIin(vehicleRequest.getIin());
                bus.setGrnz(vehicleRequest.getGrnz());
                bus.setType(String.valueOf(VehicleTypes.BUS));
                bus.setSeats(Integer.parseInt(vehicleRequest.getAttributes().get(0).getValue()));
                return vehicleService.saveBus(bus);
            case "FREIGHT":
                Freight freight = new Freight();
                freight.setUserIin(vehicleRequest.getIin());
                freight.setGrnz(vehicleRequest.getGrnz());
                freight.setType(String.valueOf(VehicleTypes.FREIGHT));
                freight.setLoadCapacity(Double.parseDouble(vehicleRequest.getAttributes().get(0).getValue()));
                return vehicleService.saveFreight(freight);
        }
        return null;
    }
}
