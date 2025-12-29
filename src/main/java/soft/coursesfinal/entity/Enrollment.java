package soft.coursesfinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_enrollments")
@Getter
@Setter
public class Enrollment extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @Column(name = "enrollment_date")
    private String enrollmentDate;

    @Column(name = "status")
    private String status;
}
