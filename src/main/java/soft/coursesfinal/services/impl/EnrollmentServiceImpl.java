package soft.coursesfinal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.EnrollmentResponseDTO;
import soft.coursesfinal.entity.Enrollment;
import soft.coursesfinal.entity.User;
import soft.coursesfinal.mappers.EnrollmentMapper;
import soft.coursesfinal.repositories.EnrollmentRepository;
import soft.coursesfinal.services.EnrollmentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) return (User) principal;
        }
        return null;
    }

    @Override
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO request) {
        if (request == null || request.getCourseId() == null) return null;

        Long userId = request.getUserId();
        if (userId == null) {
            User current = getCurrentUser();
            if (current != null) userId = current.getId();
        }
        if (userId == null) return null;

        // prevent duplicates
        Enrollment existing = enrollmentRepository.findByUserIdAndCourseId(userId, request.getCourseId());
        if (existing != null) {
            return enrollmentMapper.toResponseDTO(existing);
        }

        EnrollmentRequestDTO normalized = EnrollmentRequestDTO.builder()
                .userId(userId)
                .courseId(request.getCourseId())
                .enrollmentDate(request.getEnrollmentDate() != null ? request.getEnrollmentDate() : LocalDate.now().toString())
                .status(request.getStatus() != null ? request.getStatus() : "ACTIVE")
                .build();

        Enrollment enrollment = enrollmentMapper.toEntity(normalized);
        return enrollmentMapper.toResponseDTO(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentResponseDTO> getMyEnrollments(Long userId) {
        if (userId == null) {
            User current = getCurrentUser();
            if (current != null) userId = current.getId();
        }
        if (userId == null) return List.of();

        List<Enrollment> list = enrollmentRepository.findByUserId(userId);
        List<EnrollmentResponseDTO> dtos = new ArrayList<>();
        for (Enrollment e : list) dtos.add(enrollmentMapper.toResponseDTO(e));
        return dtos;
    }

    @Override
    public List<EnrollmentResponseDTO> getAll() {
        List<Enrollment> list = enrollmentRepository.findAll();
        List<EnrollmentResponseDTO> dtos = new ArrayList<>();
        for (Enrollment e : list) dtos.add(enrollmentMapper.toResponseDTO(e));
        return dtos;
    }

    @Override
    public void cancelEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
