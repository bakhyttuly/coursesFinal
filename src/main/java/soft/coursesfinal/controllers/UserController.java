package soft.coursesfinal.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.dto.UserResponseDTO;
import soft.coursesfinal.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO register(@RequestBody UserRequestDTO request) {
        return userService.register(request);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}/profile")
    @PreAuthorize("isAuthenticated()")
    public UserResponseDTO updateProfile(@PathVariable Long id, @RequestParam String fullName) {
        return userService.updateProfile(id, fullName);
    }

    @PutMapping("/{id}/change-password")
    @PreAuthorize("isAuthenticated()")
    public String changePassword(@PathVariable Long id,
                                 @RequestParam String oldPass,
                                 @RequestParam String newPass) {
        boolean success = userService.changePassword(id, oldPass, newPass);
        return success ? "Пароль успешно изменен" : "Ошибка: старый пароль неверный";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
