package kz.auto_life.vehicleservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Vehicle implements Serializable {

    public static final int MCI = 3063;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;

    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;

    @Column(name = "user_iin")
    private String userIin;

    @Column(name = "grnz", unique = true)
    private String grnz;

    @Column(name = "vehicle_type")
    private String type;

    @Column(name = "deleted", nullable = false)
    @JsonIgnore
    private Boolean deleted = false;

    @JsonIgnore
    public abstract int getTaxAmount();
}
