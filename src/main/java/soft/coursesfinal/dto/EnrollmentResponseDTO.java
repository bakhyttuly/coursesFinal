package soft.coursesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private CourseResponseDTO course;
    private String enrollmentDate;
    private String status;
}
