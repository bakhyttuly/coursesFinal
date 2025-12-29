package soft.coursesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponseDTO {
    private Long id;
    private CourseResponseDTO course;
    private String title;
    private String content;
    private String videoUrl;
    private int lessonOrder;
}
