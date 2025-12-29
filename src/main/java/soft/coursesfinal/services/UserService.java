package soft.coursesfinal.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.dto.UserResponseDTO;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponseDTO register(UserRequestDTO request);
    UserResponseDTO updateProfile(Long id, String fullName);
    boolean changePassword(Long userId, String oldPass, String newPass);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getById(Long id);
    void deleteUser(Long id);
}
