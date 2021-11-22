package SBCRUDBootstrap.service;

import SBCRUDBootstrap.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRoleById(Long id);

    List<Role> getAllRoles();

    Role getRole(Long id);

    Role getRoleByRole(String role);
}
