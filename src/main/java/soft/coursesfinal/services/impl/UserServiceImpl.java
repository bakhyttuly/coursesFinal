package soft.coursesfinal.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.dto.UserResponseDTO;
import soft.coursesfinal.entity.Permission;
import soft.coursesfinal.entity.User;
import soft.coursesfinal.mappers.UserMapper;
import soft.coursesfinal.repositories.PermissionRepository;
import soft.coursesfinal.repositories.UserRepository;
import soft.coursesfinal.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("User Not Found");
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @Override
    public UserResponseDTO register(UserRequestDTO request) {
        if (request == null || request.getEmail() == null) return null;

        User checkUser = userRepository.findByEmail(request.getEmail());
        if (Objects.isNull(checkUser)) {
            User user = userMapper.toEntity(request);

            Permission defaultRole = permissionRepository.findByPermission("ROLE_STUDENT");
            List<Permission> roles = new ArrayList<>();
            if (defaultRole != null) roles.add(defaultRole);
            user.setRoles(roles);

            // For study projects only. Use encoder in real apps.
            user.setPassword(request.getPassword());

            return userMapper.toResponseDTO(userRepository.save(user));
        }
        return null;
    }

    @Override
    public UserResponseDTO updateProfile(Long id, String fullName) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFullName(fullName);
            return userMapper.toResponseDTO(userRepository.save(user));
        }
        return null;
    }

    @Override
    public boolean changePassword(Long userId, String oldPass, String newPass) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null && user.getPassword().equals(oldPass)) {
            user.setPassword(newPass);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean updateCurrentPassword(String oldPass, String newPass) {
        User currentUser = getCurrentUser();
        if (currentUser != null && currentUser.getPassword().equals(oldPass)) {
            currentUser.setPassword(newPass);
            userRepository.save(currentUser);
            return true;
        }
        return false;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> dtos = new ArrayList<>();
        for (User u : users) {
            dtos.add(userMapper.toResponseDTO(u));
        }
        return dtos;
    }

    @Override
    public UserResponseDTO getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return (user != null) ? userMapper.toResponseDTO(user) : null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
