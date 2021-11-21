package cinema.spring.config;

import cinema.spring.model.Role;
import cinema.spring.model.RoleName;
import cinema.spring.model.User;
import cinema.spring.service.RoleService;
import cinema.spring.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {
    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService,
                           UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.add(userRole);
        User userAdmin = new User();
        userAdmin.setEmail("admin@i.ua");
        userAdmin.setPassword("admin123");
        userAdmin.setRoles(Set.of(adminRole));
        userService.add(userAdmin);
        User user = new User();
        user.setEmail("user@i.ua");
        user.setPassword("useruser");
        user.setRoles(Set.of(userRole));
        userService.add(user);
    }
}
