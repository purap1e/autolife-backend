package kz.auto_life.taxservice.services.impl;

import kz.auto_life.taxservice.enums.VehicleTypes;
import kz.auto_life.taxservice.exceptions.GrnzExistsException;
import kz.auto_life.taxservice.models.Vehicle;
import kz.auto_life.taxservice.models.childs.Bus;
import kz.auto_life.taxservice.models.childs.Car;
import kz.auto_life.taxservice.models.childs.Freight;
import kz.auto_life.taxservice.payload.TaxResponse;
import kz.auto_life.taxservice.repositories.BusRepository;
import kz.auto_life.taxservice.repositories.CarRepository;
import kz.auto_life.taxservice.repositories.FreightRepository;
import kz.auto_life.taxservice.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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


    public <T extends Vehicle> List<TaxResponse> getMappedList(List<T> vehicles) {
        return vehicles
                .stream()
                .map(vehicle -> new TaxResponse(
                        vehicle.getUserIin(),
                        vehicle.getGrnz(),
                        vehicle.getType(),
                        vehicle.getTaxAmount()))
                .toList();
    }

    @Override
    public List<TaxResponse> getAll() {
        List<TaxResponse> allTaxes = new ArrayList<>();
        allTaxes.addAll(getMappedList(carRepository.findAll()));
        allTaxes.addAll(getMappedList(busRepository.findAll()));
        allTaxes.addAll(getMappedList(freightRepository.findAll()));
        return allTaxes;
    }
}
