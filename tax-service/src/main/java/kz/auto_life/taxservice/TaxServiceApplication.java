package kz.auto_life.taxservice;

import kz.auto_life.taxservice.enums.VehicleTypes;
import kz.auto_life.taxservice.models.childs.Bus;
import kz.auto_life.taxservice.models.childs.Car;
import kz.auto_life.taxservice.models.childs.Freight;
import kz.auto_life.taxservice.services.VehicleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class TaxServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner run(VehicleService vehicleService) {
        return args -> {
            Car car = new Car();
            car.setGrnz("bT321d");
            car.setType(String.valueOf(VehicleTypes.CAR));
            car.setUserIin("000000000000");
            car.setEngineCapacity(1759);

            Car car1 = new Car();
            car1.setGrnz("sT131x");
            car1.setType(String.valueOf(VehicleTypes.CAR));
            car1.setUserIin("000000000001");
            car1.setEngineCapacity(3204);

            Bus bus = new Bus();
            bus.setGrnz("aF131d");
            bus.setType(String.valueOf(VehicleTypes.BUS));
            bus.setUserIin("000000000001");
            bus.setSeats(14);

            Bus bus1 = new Bus();
            bus1.setGrnz("zA000a");
            bus1.setType(String.valueOf(VehicleTypes.BUS));
            bus1.setUserIin("000000000002");
            bus1.setSeats(24);

            Freight freight = new Freight();
            freight.setGrnz("aD131z");
            freight.setType(String.valueOf(VehicleTypes.FREIGHT));
            freight.setUserIin("000000000003");
            freight.setLoadCapacity(2.3);


            Car returnedCar = vehicleService.saveCar(car);
//            vehicleService.saveCar(car1);
//            vehicleService.saveBus(bus);
//            vehicleService.saveBus(bus1);
//            vehicleService.saveFreight(freight);
        };
    }
}
