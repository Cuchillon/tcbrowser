package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Config;

public class BrowserFactory {

    private ApplicationManager app;

    public BrowserFactory(ApplicationManager app) {
        this.app = app;
    }

    public Browser getBrowser() {
        Browser browser;

        switch (Config.TEST_ENV_TYPE) {
            case EnvType.TEST_CONTAINERS:
                browser = new TestContainerBrowser(app);
                break;
            case EnvType.REMOTE:
                browser = new RemoteBrowser(app);
                break;
            default:
                throw new IllegalStateException("Wrong environment type!");
        }

        return browser;
    }
}
