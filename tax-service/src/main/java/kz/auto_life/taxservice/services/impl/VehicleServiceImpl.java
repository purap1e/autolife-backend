package kz.auto_life.taxservice.services.impl;

import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Bus;
import kz.auto_life.taxservice.models.childs.Car;
import kz.auto_life.taxservice.models.childs.Freight;
import kz.auto_life.taxservice.repositories.BusRepository;
import kz.auto_life.taxservice.repositories.CarRepository;
import kz.auto_life.taxservice.repositories.FreightRepository;
import kz.auto_life.taxservice.repositories.VehicleRepository;
import kz.auto_life.taxservice.services.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BusRepository busRepository;
    private final CarRepository carRepository;
    private final FreightRepository freightRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, BusRepository busRepository, CarRepository carRepository, FreightRepository freightRepository) {
        this.vehicleRepository = vehicleRepository;
        this.busRepository = busRepository;
        this.carRepository = carRepository;
        this.freightRepository = freightRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Freight saveFreight(Freight freight) {
        return freightRepository.save(freight);
    }
}
