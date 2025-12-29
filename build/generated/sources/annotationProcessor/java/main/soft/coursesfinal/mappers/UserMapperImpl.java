package soft.coursesfinal.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import soft.coursesfinal.dto.PermissionResponseDTO;
import soft.coursesfinal.dto.UserRequestDTO;
import soft.coursesfinal.dto.UserResponseDTO;
import soft.coursesfinal.entity.Permission;
import soft.coursesfinal.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-29T06:34:37+0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.2.1.jar, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        user.setFullName( dto.getFullName() );

        return user;
    }

    @Override
    public UserResponseDTO toResponseDTO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.id( entity.getId() );
        userResponseDTO.email( entity.getEmail() );
        userResponseDTO.fullName( entity.getFullName() );
        userResponseDTO.roles( permissionListToPermissionResponseDTOList( entity.getRoles() ) );

        return userResponseDTO.build();
    }

    protected List<PermissionResponseDTO> permissionListToPermissionResponseDTOList(List<Permission> list) {
        if ( list == null ) {
            return null;
        }

        List<PermissionResponseDTO> list1 = new ArrayList<PermissionResponseDTO>( list.size() );
        for ( Permission permission : list ) {
            list1.add( permissionMapper.toResponseDTO( permission ) );
        }

        return list1;
    }
}
