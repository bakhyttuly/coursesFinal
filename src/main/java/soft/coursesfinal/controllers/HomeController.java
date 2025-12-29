package soft.coursesfinal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import soft.coursesfinal.entity.User;
import soft.coursesfinal.services.CourseService;
import soft.coursesfinal.services.EnrollmentService;
import soft.coursesfinal.services.LessonService;
import soft.coursesfinal.services.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final EnrollmentService enrollmentService;

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User) return (User) auth.getPrincipal();
        return null;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model) {
        model.addAttribute("currentUser", getUser());
        return "profile";
    }

    @GetMapping("/courses")
    public String coursesPage(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "courses";
    }

    @GetMapping("/courses/{id}")
    public String courseDetailsPage(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getById(id));
        model.addAttribute("lessons", lessonService.getByCourseId(id));
        return "course-details";
    }

    @GetMapping("/my-courses")
    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    public String myCourses(Model model) {
        User user = getUser();
        model.addAttribute("currentUser", user);
        if (user != null) {
            model.addAttribute("enrollments", enrollmentService.getMyEnrollments(user.getId()));
        }
        return "my-courses";
    }

    @GetMapping("/instructor-panel")
    @PreAuthorize("hasAuthority('ROLE_INSTRUCTOR')")
    public String instructorPanel(Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("lessons", lessonService.getAll());
        return "instructor-panel";
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin-users";
    }

    @GetMapping("/admin/courses")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminCourses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("users", userService.getAllUsers());
        return "admin-courses";
    }
}
