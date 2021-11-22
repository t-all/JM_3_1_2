package SBCRUDBootstrap.service;

import SBCRUDBootstrap.model.Role;
import SBCRUDBootstrap.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class UserDataLoader {

    RoleService roleService;
    UserService userService;

    @Autowired
    public UserDataLoader(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void loadDataUser() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleService.addRole(role1);
        roleService.addRole(role2);

        User user1 = new User("Bob", "Bobov", (byte) 23, "bob@test.com", "qwe");
        User user2 = new User("Tom", "Tomov", (byte) 15, "tom@test.com", "qwe");
        User user3 = new User("Bil", "Bilov", (byte) 32, "bil@test.com", "qwe");
        User user4 = new User("Sam", "Samov", (byte) 19, "sam@test.com", "qwe");
        User user5 = new User("Jim", "Jimov", (byte) 27, "jim@test.com", "qwe");

        user1.setRoles(Set.of(role1, role2));
        user2.setRoles(Set.of(role2));
        user3.setRoles(Set.of(role1));
        user4.setRoles(Set.of(role2));
        user5.setRoles(Set.of(role2));
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);
    }
}
