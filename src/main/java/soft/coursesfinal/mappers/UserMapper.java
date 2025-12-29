package soft.coursesfinal.mappers;

import org.mapstruct.Mapper;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.dto.UserResponseDTO;
import soft.coursesfinal.entity.User;

@Mapper(componentModel = "spring", uses = {PermissionMapper.class})
public interface UserMapper {
    User toEntity(UserRequestDTO dto);
    UserResponseDTO toResponseDTO(User entity);
}
