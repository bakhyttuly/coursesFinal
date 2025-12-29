package soft.coursesfinal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.CourseResponseDTO;
import soft.coursesfinal.entity.Course;
import soft.coursesfinal.mappers.CourseMapper;
import soft.coursesfinal.repositories.CourseRepository;
import soft.coursesfinal.services.CourseService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponseDTO create(CourseRequestDTO request) {
        return courseMapper.toResponseDTO(courseRepository.save(courseMapper.toEntity(request)));
    }

    @Override
    public List<CourseResponseDTO> getAll() {
        List<Course> list = courseRepository.findAll();
        List<CourseResponseDTO> dtos = new ArrayList<>();
        for (Course c : list) dtos.add(courseMapper.toResponseDTO(c));
        return dtos;
    }

    @Override
    public CourseResponseDTO getById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return course != null ? courseMapper.toResponseDTO(course) : null;
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
