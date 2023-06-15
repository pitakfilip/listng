package sk.fmfi.listng.user.application.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.user.domain.Permission;
import sk.fmfi.listng.user.domain.SystemRole;
import sk.fmfi.listng.user.domain.User;
import sk.fmfi.listng.user.application.assembler.PermissionAssembler;
import sk.fmfi.listng.user.application.assembler.UserAssembler;
import sk.fmfi.listng.user.application.repository.PermissionRepository;
import sk.fmfi.listng.user.application.repository.UserRepository;
import sk.fmfi.listng.user.operations.PermissionOperations;
import sk.fmfi.listng.user.util.PasswordGenerator;
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
                    dto.role = usersOperationDto.role;
                    User user = UserAssembler.fromDto(dto);
                    user.setId(null);
                    String password = PasswordGenerator.create();
//                    generatedPasswords.put(user.getEmail(), password);
                    generatedPasswords.put(user.getEmail(), "a"); // temporary heslo 'a'
                    user.setPassword(passwordEncoder.encode(password));
                    return user;
                }).toList();

        List<User> createdUsers = userRepository.saveAll(newUsers);
        
        if (usersOperationDto.permissions != null || usersOperationDto.permissions.size() > 0) {
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
        }

        for (User user : createdUsers) {
            adminService.sendNewAccountNotification(user.getEmail(), generatedPasswords.get(user.getEmail()));
        }
    }

    @Transactional
    public void updateUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setEmail(userDto.email);
            user.setName(userDto.name);
            user.setRole(SystemRole.valueOf(userDto.role));

            Set<Permission> target = PermissionAssembler.fromDto(userDto.permissions);
            PermissionOperations.updateUserPermissions(user, target);
            userRepository.saveAndFlush(user);
        }
    }

    @Transactional
    public void updateUsers(UsersOperationDto dto) {
        List<Permission> forRemoval = new ArrayList<>();
        List<Permission> forSave = new ArrayList<>();
        PermissionAssembler.fromDto(dto.permissions).forEach(permission -> {
            if (permission.getId() == null) {
                forSave.add(permission);
            } else {
                forRemoval.add(permission);
            }
        });

        List<Long> userIds = dto.users.stream()
                .map(user -> user.id)
                .toList();
        List<User> users = userRepository.findAllByIdIn(userIds);
        PermissionOperations.bulkUpdateUser(users, SystemRole.valueOf(dto.role), forSave, forRemoval);
        userRepository.saveAll(users);
    }
}
