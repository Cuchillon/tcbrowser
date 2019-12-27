package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;

public class BrowserFactory {

    private ApplicationManager app;

    public BrowserFactory(ApplicationManager app) {
        this.app = app;
    }

    public Browser getBrowser(String browserType) {
        Browser browser;

        switch (app.getProperty(Property.TEST_ENV_TYPE)) {
            case EnvType.TEST_CONTAINERS:
                browser = new TestContainerBrowser(app, browserType);
                break;
            case EnvType.REMOTE:
                browser = new RemoteBrowser(app, browserType);
                break;
            default:
                throw new IllegalStateException("Wrong environment type!");
        }

        return browser;
    }
}
