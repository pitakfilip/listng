package sk.fmfi.listng.user.application.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.user.domain.Permission;
import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.user.application.assembler.PermissionAssembler;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.repository.PermissionRepository;
import sk.fmfi.listng.user.application.repository.UserRepository;
import sk.fmfi.listng.user.application.util.PasswordGenerator;
import sk.fmfi.listng.user.enums.SystemRole;
import sk.fmfi.listng.user.dto.UsersOperationDto;
import sk.fmfi.listng.user.dto.UserDto;

import java.util.*;

@Service
public class UserSaveService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserAdminService adminService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void createUsers(UsersOperationDto usersOperationDto) {
        Map<String, String> generatedPasswords = new HashMap<>();
        List<User> newUsers = usersOperationDto.users.stream()
                .map(dto -> {
                    dto.role = SystemRole.valueOf(usersOperationDto.role);
                    User user = UserAssembler.fromDto(dto);
                    user.setId(null);
                    String password = PasswordGenerator.create();
                    generatedPasswords.put(user.getEmail(), password);
                    user.setPassword(passwordEncoder.encode(password));
                    return user;
                }).toList();

        List<User> createdUsers = userRepository.saveAll(newUsers);

        List<Permission> newPermissions = new ArrayList<>();
        for (User user : createdUsers) {
            Set<Permission> rawPermissions = PermissionAssembler.fromDto(usersOperationDto.permissions);
            rawPermissions.forEach(permission -> {
                permission.setId(null);
                permission.setUserId(user.getId());

            });
            newPermissions.addAll(rawPermissions);
        }

        permissionRepository.saveAll(newPermissions);

        for (User user : createdUsers) {
            adminService.sendNewAccountNotification(user.getEmail(), generatedPasswords.get(user.getEmail()));
        }
    }

    @Transactional
    public void updateUser(UserDto userDto) {
//        Optional<User> optionalUser = userRepository.findById(userDto.id);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            Map<Long, PermissionDto> setPermissions = new HashMap<>();
//            List<Permission> unsetPermissions = new ArrayList<>();
//            userDto.permissions.forEach(permissionDto -> {
//                if (permissionDto.id != null) {
//                    setPermissions.put(permissionDto.id, permissionDto);
//                } else {
//                    permissionDto.userId = user.getId();
//                    unsetPermissions.add(PermissionAssembler.fromDto(permissionDto));
//                }
//            });
//
//            // DELETE REMOVED
//            List<Long> removedPermissions = new ArrayList<>();
//            user.getPermissions().forEach(permission -> {
//                Long id = permission.getId();
//                if (!setPermissions.containsKey(id)) {
//                    removedPermissions.add(id);
//                }
//            });
//            permissionRepository.deleteAllByIdInBatch(removedPermissions);
//
//            // SAVE NEW
//            permissionRepository.saveAll(unsetPermissions);
//
//            // UPDATE EXISTING
//            List<Permission> update = setPermissions.values().stream()
//                    .map(PermissionAssembler::fromDto)
//                    .toList();
//
//            update = permissionRepository.saveAll(update);
//            user.setName(userDto.name);
//            user.setEmail(userDto.email);
////            user.setRole(userDto.role);
//            user.setPermissions(new HashSet<>(update));
//            userRepository.save(user);
//        }
    }

    @Transactional
    public void updateUsers(UsersOperationDto dto) {
        List<Long> ids = dto.users.stream()
                .map(user -> user.id)
                .toList();

        
        List<User> users = userRepository.findAllByIdIn(ids);
        boolean roleChanged = false;
        for (User user : users) {
            if (!user.getRole().equals(dto.role)) {
                roleChanged = true;
            }
            user.setRole(sk.fmfi.listng.user.domain.SystemRole.valueOf(dto.role));
        }
        
        for (User user : users) {
            Set<Permission> updated = new HashSet<>();
            if (roleChanged) {
                permissionRepository.deleteAllByUserId(user.getId());
                
                List<Permission> newPermissions = new ArrayList<>();
                dto.permissions.forEach(permissionDto -> {
                    permissionDto.userId = user.getId();
                    newPermissions.add(PermissionAssembler.fromDto(permissionDto));
                });
                
                
            } else {
                List<Permission> current = user.getPermissions().stream().toList();
            }
            
        }
        
        
        userRepository.saveAll(users);
    }
}
