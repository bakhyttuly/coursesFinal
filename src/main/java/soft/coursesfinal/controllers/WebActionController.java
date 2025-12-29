package soft.coursesfinal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import soft.coursesfinal.dto.CourseRequestDTO;
import soft.coursesfinal.dto.EnrollmentRequestDTO;
import soft.coursesfinal.dto.LessonRequestDTO;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.services.CourseService;
import soft.coursesfinal.services.EnrollmentService;
import soft.coursesfinal.services.LessonService;
import soft.coursesfinal.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class WebActionController {

    private final UserService userService;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final EnrollmentService enrollmentService;

    /**
     * Register from a classic HTML <form> (application/x-www-form-urlencoded).
     */
    @PostMapping(value = "/users/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerFromForm(@ModelAttribute UserRequestDTO dto) {
        userService.register(dto);
        return "redirect:/login?success";
    }

    /**
     * Create course from admin form.
     */
    @PostMapping(value = "/courses", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String createCourseFromForm(@ModelAttribute CourseRequestDTO dto) {
        courseService.create(dto);
        return "redirect:/admin/courses?success";
    }

    /**
     * Create lesson from instructor form.
     */
    @PostMapping(value = "/lessons", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public String createLessonFromForm(@ModelAttribute LessonRequestDTO dto) {
        lessonService.create(dto);
        return "redirect:/instructor-panel?success";
    }

    /**
     * Enroll from student form.
     */
    @PostMapping(value = "/enrollments/enroll", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String enrollFromForm(@ModelAttribute EnrollmentRequestDTO dto) {
        enrollmentService.enroll(dto);
        return "redirect:/my-courses?success";
    }
}
