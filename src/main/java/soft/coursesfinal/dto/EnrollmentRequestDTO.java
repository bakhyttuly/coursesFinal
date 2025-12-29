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
    // optional: if not provided, service can use current authenticated user
    private Long userId;
    private Long courseId;
    private String enrollmentDate;
    private String status;
}
