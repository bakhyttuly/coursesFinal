package soft.coursesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDTO {
    private String name;
    private String description;
    private String level;
    private Double price;
    private Long instructorId;
}
