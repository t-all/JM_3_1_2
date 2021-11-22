package SBCRUDBootstrap.repository;

import SBCRUDBootstrap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRole(String role);
}