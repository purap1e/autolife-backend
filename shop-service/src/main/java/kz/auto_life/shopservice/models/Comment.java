package kz.auto_life.shopservice.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "user_name")
    private String name;
}
