package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.*;

public class TestContainerBrowser extends Browser {

    private BrowserWebDriverContainer browserContainer;

    public TestContainerBrowser(ApplicationManager app) {
        super(app);
    }

    @Override
    protected WebDriver createWebDriver(String browserType) {
        browserContainer = new BrowserWebDriverContainer();
        String recordingMode = System.getProperty("record", "skip");

        if (browserType.equals(BrowserType.FIREFOX)) {
            browserContainer.withCapabilities(getFirefoxOptions());
        }
        else {
            browserContainer.withCapabilities(getChromeOptions());
        }

        if (recordingMode.equals("all")) {
            browserContainer.withRecordingMode(RECORD_ALL, new File("./build/"));
        } else if (recordingMode.equals("fail")) {
            browserContainer.withRecordingMode(RECORD_FAILING, new File("./build/"));
        }

        browserContainer.start();

        return browserContainer.getWebDriver();
    }

    @Override
    public void closeBrowser() {
        if (browserContainer != null) {
            if (getDriver() != null) {
                getDriver().close();
            }
            browserContainer.stop();
        }
    }
}
