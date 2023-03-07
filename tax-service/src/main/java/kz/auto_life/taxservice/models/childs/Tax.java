package kz.auto_life.taxservice.models.childs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.auto_life.taxservice.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "taxes")
public class Tax extends BaseEntity {

    // 1 - created
    // 2 - paid
    @Column(name = "status", nullable = false)
    @JsonIgnore
    private int status = 1;

    @Column(name = "amount")
    private int amount;

//    @ManyToOne(fetch = EAGER)
//    @JoinColumn(name="vehicle_id")
//    private Vehicle vehicle;
}
