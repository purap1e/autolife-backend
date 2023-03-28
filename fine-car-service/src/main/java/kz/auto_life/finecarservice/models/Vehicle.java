package kz.auto_life.finecarservice.models;

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

    @Column(name = "user_iin")
    private String userIin;

    @Column(name = "grnz", unique = true)
    private String grnz;

    @Column(name = "vehicle_type")
    private String type;
}
