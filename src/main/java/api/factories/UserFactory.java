package api.factories;

import api.generators.RandomData;
import api.models.Roles;
import api.models.User;

public class UserFactory {
    public User create() {
        return create(RandomData.getString(), RandomData.getString(), null);
    }

    public User create(Roles roles) {
        return create(RandomData.getString(), RandomData.getString(), roles);
    }

    public User create(String username, String password, Roles roles) {
        return User.builder()
                .username(username)
                .password(password)
                .roles(roles)
                .build();
    }
}
