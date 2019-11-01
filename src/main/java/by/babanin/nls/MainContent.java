package by.babanin.nls;

import by.babanin.config.ConfigProperties;

public class MainContent extends Content {
    private static final MainContent INSTANCE = new MainContent();
    public MainContent() {
        super(ConfigProperties.getContentMainPage());
    }

    public static MainContent getInstance() {
        return INSTANCE;
    }
}
