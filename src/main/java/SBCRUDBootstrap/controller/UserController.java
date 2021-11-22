package SBCRUDBootstrap.controller;

import SBCRUDBootstrap.model.Role;
import SBCRUDBootstrap.model.User;
import SBCRUDBootstrap.service.RoleService;
import SBCRUDBootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping(value = "/admin")
    public String listUsers(@AuthenticationPrincipal User user, @AuthenticationPrincipal Role role, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("allUsers", userService.getAllUsers());
        return "admin";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/create")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "editRoles") String[] editRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : editRoles) {
            roleSet.add(roleService.getRoleByRole(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);

        return "redirect:/admin";
    }


    @PutMapping(value = "/edit/{id}")
    public String update(@ModelAttribute User user, @RequestParam(value = "editRoles") String[] editRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String rolesAdd : editRoles) {
            roleSet.add(roleService.getRoleByRole(rolesAdd));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}