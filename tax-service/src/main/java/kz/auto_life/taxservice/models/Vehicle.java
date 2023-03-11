package kz.auto_life.taxservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {

    @Column(name = "vehicle_key")
    private String key;

    @Column(name = "vehicle_value")
    private String value;
}
