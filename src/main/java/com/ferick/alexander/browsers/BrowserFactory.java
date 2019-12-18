package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;

public class BrowserFactory {

    private ApplicationManager app;

    public BrowserFactory(ApplicationManager app) {
        this.app = app;
    }

    public Browser getBrowser() {
        Browser browser;
        String envType = System.getProperty("orchestrator", EnvType.TEST_CONTAINERS);

        switch (envType) {
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
