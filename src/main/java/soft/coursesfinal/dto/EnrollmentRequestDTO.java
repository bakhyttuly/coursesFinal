package soft.coursesfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequestDTO {
    private Long userId;
    private Long courseId;
    private String enrollmentDate;
    private String status;
}
