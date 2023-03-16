package kz.auto_life.cardservice.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "service_id")
    private Long serviceId;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_description")
    private String serviceDescription;

    @Column(name = "service_amount")
    private int serviceAmount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "user_id")
    private Long userId;
}
