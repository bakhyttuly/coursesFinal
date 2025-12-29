package soft.coursesfinal.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.dto.UserResponseDTO;
import soft.coursesfinal.entity.User;
import soft.coursesfinal.mappers.UserMapper;
import soft.coursesfinal.repositories.PermissionRepository;
import soft.coursesfinal.repositories.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PermissionRepository permissionRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void register_success() {
        UserRequestDTO dto = new UserRequestDTO();
        dto.setEmail("test@test.com");
        dto.setPassword("1234");

        User user = new User();

        // email свободен
        when(userRepository.findByEmail(dto.getEmail()))
                .thenReturn(null);

        // пароль кодируется
        when(passwordEncoder.encode(dto.getPassword()))
                .thenReturn("ENCODED");

        // пользователь сохраняется
        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        // mapper возвращает DTO
        when(userMapper.toResponseDTO(any(User.class)))
                .thenReturn(new UserResponseDTO());

        UserResponseDTO result = userService.register(dto);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}
