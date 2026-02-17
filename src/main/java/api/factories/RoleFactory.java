package api.factories;

import api.models.Role;

public class RoleFactory {
    public Role create() {
        return Role.builder().build();
    }

    public Role create(String roleId, String scope) {
        return Role.builder()
                .roleId(roleId)
                .scope(scope)
                .build();
    }
}
