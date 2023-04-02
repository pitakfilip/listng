package sk.fmfi.listng.user.application.assembler;

import sk.fmfi.listng.domain.enums.SystemRole;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.api.dto.UserAuthDto;
import sk.fmfi.listng.user.api.dto.UserDto;

import java.util.List;

public class UserAssembler {

    private UserAssembler() {
        throw new IllegalStateException("Utility class");
    }
    
    public static UserAuthDto toAuthDto(User user) {
        UserAuthDto dto = new UserAuthDto();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.password = user.getPassword();
        dto.role = user.getRole().getName();
        dto.permissions = PermissionAssembler.toDto(user.getPermissions());
        
        return dto;
    }

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.email = user.getEmail();
        dto.name = user.getName();
        dto.role = user.getRole().getName();
        dto.permissions = PermissionAssembler.toDto(user.getPermissions());

        return dto;
    }
    
    public static List<UserDto> toDto(List<User> users) {
        return users.stream()
                .map(UserAssembler::toDto)
                .toList();
    }
    
    public static User fromDto(UserAuthDto dto) {
        User user = new User(dto.name, dto.email, dto.password, dto.role);
        user.setId(dto.id);
        if (dto.permissions != null && !dto.permissions.isEmpty()) {
            user.setPermissions(PermissionAssembler.fromDto(dto.permissions));
        }
        return user;
    }
    
    public static User newFromDto(UserAuthDto dto) {
        User user = new User(dto.name, dto.email, dto.password, dto.role);
        if (dto.permissions != null && !dto.permissions.isEmpty()) {
            user.setPermissions(PermissionAssembler.fromDto(dto.permissions));
        }
        return user;
    }
}
