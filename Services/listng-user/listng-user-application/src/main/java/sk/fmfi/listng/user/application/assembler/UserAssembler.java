package sk.fmfi.listng.user.application.assembler;

import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.user.enums.SystemRole;
import sk.fmfi.listng.user.dto.UserAuthDto;
import sk.fmfi.listng.user.dto.UserDto;

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
        dto.role = SystemRole.valueOf(user.getRole().getName());
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
    
    public static User fromDto(UserDto dto) {
        User user = new User(dto.name, dto.email, dto.role);
        if (dto.id != null) {
            user.setId(dto.id);
        }
        return user;
    }
    
    public static List<User> fromDto(List<UserDto> dtos) {
        return dtos.stream()
                .map(UserAssembler::fromDto)
                .toList();
    }
    
}
