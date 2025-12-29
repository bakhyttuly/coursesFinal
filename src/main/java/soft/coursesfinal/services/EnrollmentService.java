package soft.coursesfinal.services;

import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDTO enroll(EnrollmentRequestDTO request);
    List<EnrollmentResponseDTO> getMyEnrollments(Long userId);
    List<EnrollmentResponseDTO> getAll();
    void cancelEnrollment(Long id);
}
