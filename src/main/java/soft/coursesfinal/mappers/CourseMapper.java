package soft.coursesfinal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.CourseResponseDTO;
import soft.coursesfinal.entity.Course;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CourseMapper {

    @Mapping(target = "instructor.id", source = "instructorId")
    Course toEntity(CourseRequestDTO dto);

    CourseResponseDTO toResponseDTO(Course entity);
}
