package soft.coursesfinal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.LessonResponseDTO;
import soft.coursesfinal.services.LessonService;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public LessonResponseDTO create(@RequestBody LessonRequestDTO request) {
        return lessonService.create(request);
    }

    @GetMapping
    public List<LessonResponseDTO> getAll() {
        return lessonService.getAll();
    }

    @GetMapping("/course/{courseId}")
    public List<LessonResponseDTO> getByCourse(@PathVariable Long courseId) {
        return lessonService.getByCourseId(courseId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public void delete(@PathVariable Long id) {
        lessonService.delete(id);
    }
}
