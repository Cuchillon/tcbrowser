package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;
import org.openqa.selenium.WebDriver;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;
import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_FAILING;

@SuppressWarnings("rawtypes")
public class TestContainerBrowser extends Browser {

    private BrowserWebDriverContainer browserContainer;

    public TestContainerBrowser(ApplicationManager app) {
        super(app);
    }

    @Override
    protected WebDriver createWebDriver() {
        browserContainer = new BrowserWebDriverContainer().withCapabilities(getCapabilities());
        setRecordingMode();
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

    private void setRecordingMode() {
        String recordingMode = app.getProperty(Property.VNC_RECORD_MODE);
        String recordPath = app.getProperty(Property.VNC_RECORD_PATH);
        if (recordingMode.equals("all")) {
            browserContainer.withRecordingMode(RECORD_ALL, new File(recordPath));
        } else if (recordingMode.equals("fail")) {
            browserContainer.withRecordingMode(RECORD_FAILING, new File(recordPath));
        }
    }
}
