package kz.auto_life.finecarservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "fines")
public class Fine {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;

    @Column(name = "user_iin")
    private String userIin;

    @Column(name = "fine_description")
    private String description;

    @Column(name = "grnz")
    private String grnz;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "amount_mci")
    private int amountMci;

    @Column(name = "paid", nullable = false)
    private Boolean paid = false;
}
