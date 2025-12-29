package soft.coursesfinal.mappers;

import org.mapstruct.Mapper;
import soft.coursesfinal.dto.PermissionRequestDTO;
import soft.coursesfinal.dto.PermissionResponseDTO;
import soft.coursesfinal.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toEntity(PermissionRequestDTO dto);
    PermissionResponseDTO toResponseDTO(Permission entity);
}
