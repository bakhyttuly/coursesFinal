package soft.coursesfinal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.LessonResponseDTO;
import soft.coursesfinal.entity.Lesson;
import soft.coursesfinal.mappers.LessonMapper;
import soft.coursesfinal.repositories.LessonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonServiceImplTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private LessonMapper lessonMapper;

    @InjectMocks
    private LessonServiceImpl lessonService;

    @Test
    void create_savesLesson() {
        LessonRequestDTO request = new LessonRequestDTO();
        Lesson lesson = new Lesson();
        LessonResponseDTO dto = new LessonResponseDTO();

        when(lessonMapper.toEntity(request)).thenReturn(lesson);
        when(lessonRepository.save(lesson)).thenReturn(lesson);
        when(lessonMapper.toResponseDTO(lesson)).thenReturn(dto);

        LessonResponseDTO result = lessonService.create(request);

        assertNotNull(result);
    }

    @Test
    void getAll_returnsLessons() {
        Lesson lesson = new Lesson();
        LessonResponseDTO dto = new LessonResponseDTO();

        when(lessonRepository.findAll()).thenReturn(List.of(lesson));
        when(lessonMapper.toResponseDTO(lesson)).thenReturn(dto);

        List<LessonResponseDTO> result = lessonService.getAll();

        assertEquals(1, result.size());
    }
}
