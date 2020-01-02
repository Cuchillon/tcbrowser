package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteBrowser extends Browser {

    public RemoteBrowser(ApplicationManager app) {
        super(app);
    }

    @Override
    protected WebDriver createWebDriver() {
        RemoteWebDriver driver;
        try {
            driver = new RemoteWebDriver(getSeleniumServerUrl(), getCapabilities());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Exception on creating instance of RemoteWebDriver", e);
        }
        return driver;
    }

    @Override
    public void closeBrowser() {
        if (getDriver() != null) {
            getDriver().close();
        }
    }

    protected URL getSeleniumServerUrl() throws MalformedURLException {
        String seleniumServerUrl = String.format("http://%s:%s%s",
                app.getProperty(Property.SELENIUM_SERVER_HOST),
                app.getProperty(Property.SELENIUM_SERVER_PORT),
                app.getProperty(Property.SELENIUM_SERVER_PATH));
        return new URL(seleniumServerUrl);
    }
}
