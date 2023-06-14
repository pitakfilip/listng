package sk.fmfi.listng.user.operations;

import sk.fmfi.listng.user.domain.Permission;
import sk.fmfi.listng.user.domain.SystemRole;
import sk.fmfi.listng.user.domain.User;

import java.util.*;

public class PermissionOperations {

    /**
     * Analyze existing user with set permissions cross with a list of permissions, which the user should have. 
     * @param user Existing user with set permission.
     * @param permissions Target permissions.
     * @return Map of Permission Lists divided based on the needed operation to perform (create new, remove existing, update existing)
     */
    public static void updateUserPermissions(User user, Set<Permission> permissions) {
        Map<Long, Permission> targetPermissions = new HashMap<>();
        permissions.forEach(perm -> {
            if (perm.getId() != null && perm.getId() >= 1) {
                targetPermissions.put(perm.getId(), perm);
            }
        });
        
        List<Permission> forRemoval = new ArrayList<>();
        for (Permission permission : user.getPermissions()) {
            permission.setUserId(user.getId());
            if (targetPermissions.containsKey(permission.getId())) {
                Permission target = targetPermissions.get(permission.getId());
                permission.setRole(target.getRole());
                permission.setStatus(target.getStatus());
                permission.setGroupId(target.getGroupId());
            } else {
                forRemoval.add(permission);
            }
        }
        
        user.removePermissions(forRemoval);

        List<Permission> unset = permissions.stream()
                .filter(permission -> permission.getId() == null || permission.getId() < 1)
                .toList();
        unset.forEach(permission -> permission.setUserId(user.getId()));
        user.addNewPermissions(unset);
    }

    /**
     * Updates users role in system and their permissions. Selected permissions may be deleted or saved (update existing or create new).
     * @param users List of users to on which perform update.
     * @param forSave List of permissions to create or update
     * @param forRemoval List of permissions to delete if applicable.
     */
    public static void bulkUpdateUser(List<User> users, SystemRole role, List<Permission> forSave, List<Permission> forRemoval) {
        List<Long> removeCourseIds = forRemoval.stream()
                .map(Permission::getCourseId)
                .toList();
        
        for (User user : users) {
            boolean changeRole = !role.equals(user.getRole());
            if (changeRole) {
                user.setRole(role);
                user.clearPermissions();
            }

            if (!changeRole) {
                user.findAndRemovePermissions(removeCourseIds);
            }
            
            for (Permission save : forSave) {
                user.savePermission(save);
            }
        }
    }
}
