package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class TestContainerBrowser extends Browser {

    public TestContainerBrowser(ApplicationManager app) {
        super(app);
    }

    @Override
    protected WebDriver createWebDriver(String browserType) {
        BrowserWebDriverContainer browserContainer;

        if (browserType.equals(BrowserType.FIREFOX)) {
            browserContainer = new BrowserWebDriverContainer().withCapabilities(getFirefoxOptions());
        }
        else {
            browserContainer = new BrowserWebDriverContainer().withCapabilities(getChromeOptions());
        }

        return browserContainer.getWebDriver();
    }
}
