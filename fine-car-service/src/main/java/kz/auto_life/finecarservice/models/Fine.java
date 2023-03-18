package kz.auto_life.finecarservice.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "fines")
public class Fine {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

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
