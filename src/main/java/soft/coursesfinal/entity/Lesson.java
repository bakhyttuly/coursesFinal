package soft.coursesfinal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_lessons")
@Getter
@Setter
public class Lesson extends BaseEntity {

    @ManyToOne
    private Course course;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "lesson_order")
    private int lessonOrder;
}
