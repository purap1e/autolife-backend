package kz.auto_life.taxservice.models.childs;

import kz.auto_life.taxservice.models.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "taxes")
public class Tax extends BaseEntity {

    // 1 - not paid
    // 2 - paid
    @Column(name = "status", nullable = false)
    private int status = 1;

    @Column(name = "amount")
    private int amount;

}
