package kz.auto_life.cardservice.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "card_number")
    @JsonIgnore
    private String cardNumber;

    @Column(name = "card_month")
    @JsonIgnore
    private int month;

    @Column(name = "card_year")
    @JsonIgnore
    private int year;

    @Column(name = "cvv")
    @JsonIgnore
    private String cvv;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "user_id")
    private UUID userId;
}
