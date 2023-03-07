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
@Table(name = "freights")
public class Freight extends Vehicle {

    @Column(name = "load_capacity")
    private double loadCapacity;

    @Override
    public int getTaxAmount() {
        if (loadCapacity <= 1) {
            return Vehicle.MCI * 3;
        } else if (loadCapacity <= 1.5) {
            return Vehicle.MCI * 5;
        } else if (loadCapacity <= 5) {
            return Vehicle.MCI * 7;
        } else {
            return Vehicle.MCI * 9;
        }
    }
}
