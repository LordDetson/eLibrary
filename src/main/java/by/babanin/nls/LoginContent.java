package by.babanin.nls;

import by.babanin.config.ConfigProperties;

public class LoginContent extends Content {
    private static final LoginContent INSTANCE = new LoginContent();

    public LoginContent() {
        super(ConfigProperties.getContentLoginPage());
    }

    public static LoginContent getInstance() {
        return INSTANCE;
    }
}
