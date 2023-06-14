package sk.fmfi.listng.rest.dto;


import sk.fmfi.listng.infrastructure.common.dto.BaseDto;
import sk.fmfi.listng.user.dto.PermissionDto;

import java.util.List;

public class ImportDto implements BaseDto {

    public int sheet;

    public int rowFrom;
    
    public int rowTo;

    public Integer name;

    public Integer surname;

    public Integer fullName;

    public int email;

    public String role;

    public List<PermissionDto> permissions;

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    public int getRowFrom() {
        return rowFrom;
    }

    public void setRowFrom(int rowFrom) {
        this.rowFrom = rowFrom;
    }

    public int getRowTo() {
        return rowTo;
    }

    public void setRowTo(int rowTo) {
        this.rowTo = rowTo;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getSurname() {
        return surname;
    }

    public void setSurname(Integer surname) {
        this.surname = surname;
    }

    public Integer getFullName() {
        return fullName;
    }

    public void setFullName(Integer fullName) {
        this.fullName = fullName;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
    }
}
