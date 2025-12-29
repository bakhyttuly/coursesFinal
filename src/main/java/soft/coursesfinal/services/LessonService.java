package soft.coursesfinal.services;

import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.LessonResponseDTO;

import java.util.List;

public interface LessonService {
    LessonResponseDTO create(LessonRequestDTO request);
    List<LessonResponseDTO> getAll();
    List<LessonResponseDTO> getByCourseId(Long courseId);
    void delete(Long id);
}
