package soft.coursesfinal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.EnrollmentResponseDTO;
import soft.coursesfinal.services.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping(value = "/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public EnrollmentResponseDTO enroll(@RequestBody EnrollmentRequestDTO request) {
        return enrollmentService.enroll(request);
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public List<EnrollmentResponseDTO> myEnrollments() {
        // service will determine current user
        return enrollmentService.getMyEnrollments(null);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public void cancel(@PathVariable Long id) {
        enrollmentService.cancelEnrollment(id);
    }
}
