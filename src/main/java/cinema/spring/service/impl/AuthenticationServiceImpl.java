package cinema.spring.service.impl;

import cinema.spring.model.Role;
import cinema.spring.model.RoleName;
import cinema.spring.model.User;
import cinema.spring.service.AuthenticationService;
import cinema.spring.service.RoleService;
import cinema.spring.service.ShoppingCartService;
import cinema.spring.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        Role role = roleService.getRoleByName(RoleName.USER.name());
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(role));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
