package sk.fmfi.listng.rest.dto;

import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.user.dto.UserDto;
import sk.fmfi.listng.user.enums.SystemRole;

import java.util.List;

public class UserBaseDto implements BaseDto {
    public long id;
    public String name;
    public String email;
    public String role;

    public UserBaseDto() {
    }

    public void setId(long id) {
        
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(SystemRole role) {
        this.role = role.name();
    }

    public static UserBaseDto of(UserDto user) {
        UserBaseDto dto = new UserBaseDto();
        dto.id = user.id;
        dto.name = user.name;
        dto.email = user.email;
        dto.role = user.role;
        
        return dto;
    }
    
    public static List<UserBaseDto> of(List<UserDto> users) {
        return users.stream()
                .map(UserBaseDto::of)
                .toList();
    }

}
