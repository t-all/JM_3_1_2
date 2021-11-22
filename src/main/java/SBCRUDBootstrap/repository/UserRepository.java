package SBCRUDBootstrap.repository;

import SBCRUDBootstrap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
}