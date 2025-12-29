package soft.coursesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequestDTO {
    private Long courseId;
    private String title;
    private String content;
    private String videoUrl;
    private int lessonOrder;
}
