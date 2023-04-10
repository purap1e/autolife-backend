package kz.auto_life.stoservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private String type;

    @Lob
    @Column(length = 10485760)
    private byte[] data;
}
