package api.enums;

import api.models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoles {
    PROJECT_ADMIN(new Role("PROJECT_ADMIN", "g"));

    private final Role role;
}
