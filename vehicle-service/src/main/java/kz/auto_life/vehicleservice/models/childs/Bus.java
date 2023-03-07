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
@Table(name = "buses")
public class Bus extends Vehicle {

    @Column(name = "seats")
    private int seats;

    @Override
    public int getTaxAmount() {
        if (seats <= 12) {
            return Vehicle.MCI * 9;
        } else if (seats <= 25) {
            return Vehicle.MCI * 14;
        } else {
            return Vehicle.MCI * 20;
        }
    }
}
