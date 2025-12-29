package soft.coursesfinal.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.EnrollmentResponseDTO;
import soft.coursesfinal.entity.Course;
import soft.coursesfinal.entity.Enrollment;
import soft.coursesfinal.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T06:34:36+0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class EnrollmentMapperImpl implements EnrollmentMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Enrollment toEntity(EnrollmentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setUser( enrollmentRequestDTOToUser( dto ) );
        enrollment.setCourse( enrollmentRequestDTOToCourse( dto ) );
        enrollment.setEnrollmentDate( dto.getEnrollmentDate() );
        enrollment.setStatus( dto.getStatus() );

        return enrollment;
    }

    @Override
    public EnrollmentResponseDTO toResponseDTO(Enrollment entity) {
        if ( entity == null ) {
            return null;
        }

        EnrollmentResponseDTO.EnrollmentResponseDTOBuilder enrollmentResponseDTO = EnrollmentResponseDTO.builder();

        enrollmentResponseDTO.id( entity.getId() );
        enrollmentResponseDTO.user( userMapper.toResponseDTO( entity.getUser() ) );
        enrollmentResponseDTO.course( courseMapper.toResponseDTO( entity.getCourse() ) );
        enrollmentResponseDTO.enrollmentDate( entity.getEnrollmentDate() );
        enrollmentResponseDTO.status( entity.getStatus() );

        return enrollmentResponseDTO.build();
    }

    protected User enrollmentRequestDTOToUser(EnrollmentRequestDTO enrollmentRequestDTO) {
        if ( enrollmentRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( enrollmentRequestDTO.getUserId() );

        return user;
    }

    protected Course enrollmentRequestDTOToCourse(EnrollmentRequestDTO enrollmentRequestDTO) {
        if ( enrollmentRequestDTO == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( enrollmentRequestDTO.getCourseId() );

        return course;
    }
}
