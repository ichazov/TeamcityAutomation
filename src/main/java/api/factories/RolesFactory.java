package api.factories;

import api.models.Role;
import api.models.Roles;

import java.util.List;

public class RolesFactory {
    public Roles create() {
        return create(List.of(new RoleFactory().create()));
    }

    public Roles create(List<Role> roles) {
        return Roles.builder()
                .role(roles)
                .build();
    }
}
