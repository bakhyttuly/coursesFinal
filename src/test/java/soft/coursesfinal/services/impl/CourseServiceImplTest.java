package soft.coursesfinal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.CourseResponseDTO;
import soft.coursesfinal.entity.Course;
import soft.coursesfinal.mappers.CourseMapper;
import soft.coursesfinal.repositories.CourseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void create_savesCourse() {
        CourseRequestDTO request = new CourseRequestDTO();
        Course course = new Course();
        CourseResponseDTO response = new CourseResponseDTO();

        when(courseMapper.toEntity(request)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toResponseDTO(course)).thenReturn(response);

        CourseResponseDTO result = courseService.create(request);

        assertNotNull(result);
        verify(courseRepository).save(course);
    }

    @Test
    void getAll_returnsCourses() {
        Course course = new Course();
        CourseResponseDTO dto = new CourseResponseDTO();

        when(courseRepository.findAll()).thenReturn(List.of(course));
        when(courseMapper.toResponseDTO(course)).thenReturn(dto);

        List<CourseResponseDTO> result = courseService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    void delete_removesCourse() {
        courseService.delete(3L);
        verify(courseRepository).deleteById(3L);
    }
}
