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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BusRepository busRepository;
    private final CarRepository carRepository;
    private final FreightRepository freightRepository;

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    @Transactional
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public Freight saveFreight(Freight freight) {
        return freightRepository.save(freight);
    }
}
