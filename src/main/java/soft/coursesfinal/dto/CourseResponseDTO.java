package soft.coursesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String level;
    private Double price;
    private UserResponseDTO instructor;
}
