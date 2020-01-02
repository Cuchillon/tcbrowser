package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;

public class BrowserFactory {

    private ApplicationManager app;

    public BrowserFactory(ApplicationManager app) {
        this.app = app;
    }

    public Browser getBrowser() {
        Browser browser;

        switch (app.getProperty(Property.TEST_ENV_TYPE)) {
            case EnvType.TEST_CONTAINERS:
                browser = new TestContainerBrowser(app);
                break;
            case EnvType.REMOTE:
                browser = new RemoteBrowser(app);
                break;
            case EnvType.LOCAL:
                browser = new LocalBrowser(app);
                break;
            default:
                throw new IllegalStateException("Wrong environment type!");
        }

        return browser;
    }
}
