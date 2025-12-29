package soft.coursesfinal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.LessonResponseDTO;
import soft.coursesfinal.entity.Lesson;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface LessonMapper {

    @Mapping(target = "course.id", source = "courseId")
    Lesson toEntity(LessonRequestDTO dto);

    LessonResponseDTO toResponseDTO(Lesson entity);
}
