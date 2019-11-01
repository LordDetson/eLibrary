package by.babanin.dao;

import java.util.HashSet;
import java.util.Set;

public class UserRepository {
    private static final UserRepository INSTANCE = new UserRepository();
    private Set<String> forbiddenUsername;

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public boolean isExistUsername(String username) {
        return forbiddenUsername.contains(username);
    }

    private UserRepository() {
        loadForbiddenUsername();
    }

    private void loadForbiddenUsername() {
        forbiddenUsername = new HashSet<>();
        forbiddenUsername.add("login");
        forbiddenUsername.add("username");
    }
}
