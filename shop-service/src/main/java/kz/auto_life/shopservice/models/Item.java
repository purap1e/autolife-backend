package kz.auto_life.shopservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @Column(name = "amount")
    private int amount;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(cascade = ALL)
    private List<Image> images = Collections.emptyList();
}
