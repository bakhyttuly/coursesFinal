package soft.coursesfinal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.EnrollmentResponseDTO;
import soft.coursesfinal.entity.Enrollment;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class})
public interface EnrollmentMapper {

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "course.id", source = "courseId")
    Enrollment toEntity(EnrollmentRequestDTO dto);

    EnrollmentResponseDTO toResponseDTO(Enrollment entity);
}
