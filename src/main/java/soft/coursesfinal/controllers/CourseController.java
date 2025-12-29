package soft.coursesfinal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.CourseResponseDTO;
import soft.coursesfinal.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CourseResponseDTO create(@RequestBody CourseRequestDTO request) {
        return courseService.create(request);
    }

    @GetMapping
    public List<CourseResponseDTO> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseResponseDTO getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}
