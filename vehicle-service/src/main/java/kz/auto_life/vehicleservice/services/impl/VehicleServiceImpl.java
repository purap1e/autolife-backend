package kz.auto_life.vehicleservice.services.impl;

import kz.auto_life.vehicleservice.enums.VehicleTypes;
import kz.auto_life.vehicleservice.exceptions.GrnzExistsException;
import kz.auto_life.vehicleservice.models.childs.Bus;
import kz.auto_life.vehicleservice.models.childs.Car;
import kz.auto_life.vehicleservice.models.childs.Freight;
import kz.auto_life.vehicleservice.repositories.BusRepository;
import kz.auto_life.vehicleservice.repositories.CarRepository;
import kz.auto_life.vehicleservice.repositories.FreightRepository;
import kz.auto_life.vehicleservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final BusRepository busRepository;
    private final CarRepository carRepository;
    private final FreightRepository freightRepository;

    private boolean grnzExists(String grnz, String type) {
        if (type.equals(String.valueOf(VehicleTypes.CAR))) {
            return carRepository.findByGrnz(grnz) != null;
        } else if (type.equals(String.valueOf(VehicleTypes.BUS))) {
            return busRepository.findByGrnz(grnz) != null;
        }
        return freightRepository.findByGrnz(grnz) != null;
    }

    @Override
    @Transactional
    public Bus saveBus(Bus bus) {
        if (grnzExists(bus.getGrnz(), bus.getType())) {
            throw new GrnzExistsException(bus.getGrnz());
        }
        log.info("Saving new bus with number '{}' to the database", bus.getGrnz());
        return busRepository.save(bus);
    }

    @Override
    @Transactional
    public Car saveCar(Car car) {
        if (grnzExists(car.getGrnz(), car.getType())) {
            throw new GrnzExistsException(car.getGrnz());
        }
        log.info("Saving new car with number '{}' to the database", car.getGrnz());
        return carRepository.save(car);
    }

    @Override
    @Transactional
    public Freight saveFreight(Freight freight) {
        if (grnzExists(freight.getGrnz(), freight.getType())) {
            throw new GrnzExistsException(freight.getGrnz());
        }
        log.info("Saving new freight with number '{}' to the database", freight.getGrnz());
        return freightRepository.save(freight);
    }
}
