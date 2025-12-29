package soft.coursesfinal.services;

import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    CourseResponseDTO create(CourseRequestDTO request);
    List<CourseResponseDTO> getAll();
    CourseResponseDTO getById(Long id);
    void delete(Long id);
}
