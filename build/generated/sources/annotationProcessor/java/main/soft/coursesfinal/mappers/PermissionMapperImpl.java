package soft.coursesfinal.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import soft.coursesfinal.dto.PermissionRequestDTO;
import soft.coursesfinal.dto.PermissionResponseDTO;
import soft.coursesfinal.entity.Permission;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T06:34:36+0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public Permission toEntity(PermissionRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setPermission( dto.getPermission() );

        return permission;
    }

    @Override
    public PermissionResponseDTO toResponseDTO(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionResponseDTO.PermissionResponseDTOBuilder permissionResponseDTO = PermissionResponseDTO.builder();

        permissionResponseDTO.id( entity.getId() );
        permissionResponseDTO.permission( entity.getPermission() );

        return permissionResponseDTO.build();
    }
}
