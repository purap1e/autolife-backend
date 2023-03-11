package kz.auto_life.taxservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "taxes")
public class Tax extends BaseEntity {

    @Column(name = "paid", nullable = false)
    private Boolean paid = false;

    @Column(name = "amount")
    private BigDecimal amount;
}
