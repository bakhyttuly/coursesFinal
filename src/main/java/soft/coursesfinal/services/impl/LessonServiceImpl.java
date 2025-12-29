package soft.coursesfinal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.LessonResponseDTO;
import soft.coursesfinal.entity.Lesson;
import soft.coursesfinal.mappers.LessonMapper;
import soft.coursesfinal.repositories.LessonRepository;
import soft.coursesfinal.services.LessonService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Override
    public LessonResponseDTO create(LessonRequestDTO request) {
        Lesson lesson = lessonMapper.toEntity(request);
        return lessonMapper.toResponseDTO(lessonRepository.save(lesson));
    }

    @Override
    public List<LessonResponseDTO> getAll() {
        List<Lesson> list = lessonRepository.findAll();
        List<LessonResponseDTO> dtos = new ArrayList<>();
        for (Lesson l : list) dtos.add(lessonMapper.toResponseDTO(l));
        return dtos;
    }

    @Override
    public List<LessonResponseDTO> getByCourseId(Long courseId) {
        List<Lesson> list = lessonRepository.findByCourseIdOrderByLessonOrderAsc(courseId);
        List<LessonResponseDTO> dtos = new ArrayList<>();
        for (Lesson l : list) dtos.add(lessonMapper.toResponseDTO(l));
        return dtos;
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }
}
