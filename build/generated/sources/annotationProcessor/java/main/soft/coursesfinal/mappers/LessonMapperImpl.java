package soft.coursesfinal.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.LessonResponseDTO;
import soft.coursesfinal.entity.Course;
import soft.coursesfinal.entity.Lesson;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T06:34:37+0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Lesson toEntity(LessonRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setCourse( lessonRequestDTOToCourse( dto ) );
        lesson.setTitle( dto.getTitle() );
        lesson.setContent( dto.getContent() );
        lesson.setVideoUrl( dto.getVideoUrl() );
        lesson.setLessonOrder( dto.getLessonOrder() );

        return lesson;
    }

    @Override
    public LessonResponseDTO toResponseDTO(Lesson entity) {
        if ( entity == null ) {
            return null;
        }

        LessonResponseDTO.LessonResponseDTOBuilder lessonResponseDTO = LessonResponseDTO.builder();

        lessonResponseDTO.id( entity.getId() );
        lessonResponseDTO.course( courseMapper.toResponseDTO( entity.getCourse() ) );
        lessonResponseDTO.title( entity.getTitle() );
        lessonResponseDTO.content( entity.getContent() );
        lessonResponseDTO.videoUrl( entity.getVideoUrl() );
        lessonResponseDTO.lessonOrder( entity.getLessonOrder() );

        return lessonResponseDTO.build();
    }

    protected Course lessonRequestDTOToCourse(LessonRequestDTO lessonRequestDTO) {
        if ( lessonRequestDTO == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( lessonRequestDTO.getCourseId() );

        return course;
    }
}
