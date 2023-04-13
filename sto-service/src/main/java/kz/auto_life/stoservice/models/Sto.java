package kz.auto_life.stoservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table(name = "sto")
public class Sto extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "reviews_count")
    private int count = 0;

    @Column(name = "average_grade")
    private double grade = 0;

    @Column(name = "location")
    private String location;

    @Column(name = "grade_sum")
    private double gradeSum = 0;

    @OneToMany(cascade = ALL)
    private List<Image> images = Collections.emptyList();
}
