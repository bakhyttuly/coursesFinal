package soft.coursesfinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_courses")
@Getter
@Setter
public class Course extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "level")
    private String level;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    private User instructor;
}
