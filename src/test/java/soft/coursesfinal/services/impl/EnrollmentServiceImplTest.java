package soft.coursesfinal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.EnrollmentResponseDTO;
import soft.coursesfinal.entity.Enrollment;
import soft.coursesfinal.mappers.EnrollmentMapper;
import soft.coursesfinal.repositories.EnrollmentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceImplTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private EnrollmentMapper enrollmentMapper;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    @Test
    void enroll_createsNewEnrollment_whenNotExists() {
        EnrollmentRequestDTO request = EnrollmentRequestDTO.builder()
                .userId(1L)
                .courseId(2L)
                .build();

        Enrollment enrollment = new Enrollment();
        EnrollmentResponseDTO responseDTO = new EnrollmentResponseDTO();

        when(enrollmentRepository.findByUserIdAndCourseId(1L, 2L)).thenReturn(null);
        when(enrollmentMapper.toEntity(any())).thenReturn(enrollment);
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);
        when(enrollmentMapper.toResponseDTO(enrollment)).thenReturn(responseDTO);

        EnrollmentResponseDTO result = enrollmentService.enroll(request);

        assertNotNull(result);
        verify(enrollmentRepository).save(enrollment);
    }

    @Test
    void enroll_returnsExistingEnrollment_whenAlreadyExists() {
        EnrollmentRequestDTO request = EnrollmentRequestDTO.builder()
                .userId(1L)
                .courseId(2L)
                .build();

        Enrollment existing = new Enrollment();
        EnrollmentResponseDTO responseDTO = new EnrollmentResponseDTO();

        when(enrollmentRepository.findByUserIdAndCourseId(1L, 2L)).thenReturn(existing);
        when(enrollmentMapper.toResponseDTO(existing)).thenReturn(responseDTO);

        EnrollmentResponseDTO result = enrollmentService.enroll(request);

        assertNotNull(result);
        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    void getMyEnrollments_returnsList() {
        Enrollment enrollment = new Enrollment();
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();

        when(enrollmentRepository.findByUserId(1L)).thenReturn(List.of(enrollment));
        when(enrollmentMapper.toResponseDTO(enrollment)).thenReturn(dto);

        List<EnrollmentResponseDTO> result = enrollmentService.getMyEnrollments(1L);

        assertEquals(1, result.size());
    }

    @Test
    void cancelEnrollment_deletesById() {
        enrollmentService.cancelEnrollment(5L);
        verify(enrollmentRepository).deleteById(5L);
    }
}
