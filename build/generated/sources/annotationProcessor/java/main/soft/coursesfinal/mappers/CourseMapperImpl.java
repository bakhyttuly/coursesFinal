package soft.coursesfinal.mappers;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.CourseResponseDTO;
import soft.coursesfinal.entity.Course;
import soft.coursesfinal.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T06:34:36+0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Course toEntity(CourseRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Course course = new Course();

        course.setInstructor( courseRequestDTOToUser( dto ) );
        course.setName( dto.getName() );
        course.setDescription( dto.getDescription() );
        course.setLevel( dto.getLevel() );
        course.setPrice( dto.getPrice() );

        return course;
    }

    @Override
    public CourseResponseDTO toResponseDTO(Course entity) {
        if ( entity == null ) {
            return null;
        }

        CourseResponseDTO.CourseResponseDTOBuilder courseResponseDTO = CourseResponseDTO.builder();

        courseResponseDTO.id( entity.getId() );
        courseResponseDTO.name( entity.getName() );
        courseResponseDTO.description( entity.getDescription() );
        courseResponseDTO.level( entity.getLevel() );
        courseResponseDTO.price( entity.getPrice() );
        courseResponseDTO.instructor( userMapper.toResponseDTO( entity.getInstructor() ) );

        return courseResponseDTO.build();
    }

    protected User courseRequestDTOToUser(CourseRequestDTO courseRequestDTO) {
        if ( courseRequestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( courseRequestDTO.getInstructorId() );

        return user;
    }
}
