package kz.auto_life.vehicleservice.models.childs;

import kz.auto_life.vehicleservice.models.Vehicle;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    @Column(name = "engine_capacity")
    private int engineCapacity;

    @Override
    public int getTaxAmount() {
        if (engineCapacity <= 1100) {
            return Vehicle.MCI;
        } else if (engineCapacity <= 1500) {
            return Vehicle.MCI * 2;
        } else if (engineCapacity <= 2000) {
            return Vehicle.MCI * 3 + (engineCapacity - 1500) * 7;
        } else if (engineCapacity <= 2500) {
            return Vehicle.MCI * 6 + (engineCapacity - 1500) * 7;
        } else if (engineCapacity <= 3000) {
            return Vehicle.MCI * 9 + (engineCapacity - 1500) * 7;
        } else if (engineCapacity <= 4000) {
            return Vehicle.MCI * 15 + (engineCapacity - 1500) * 7;
        } else if (engineCapacity <= 5000) {
            return Vehicle.MCI * 117 + (engineCapacity - 1500) * 7;
        } else {
            return Vehicle.MCI * 200 + (engineCapacity - 1500) * 7;
        }
    }
}
