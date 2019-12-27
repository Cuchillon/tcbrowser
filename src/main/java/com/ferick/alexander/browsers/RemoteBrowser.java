package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteBrowser extends Browser {

    public RemoteBrowser(ApplicationManager app, String browserType) {
        super(app, browserType);
    }

    @Override
    protected WebDriver createWebDriver() {
        String seleniumServerUrl = getSeleniumServerUrl();
        RemoteWebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL(seleniumServerUrl), getCapabilities());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Exception on creating instance of WebDriver with URL " + seleniumServerUrl, e);
        }
        return driver;
    }

    @Override
    public void closeBrowser() {
        if (getDriver() != null) {
            getDriver().close();
        }
    }

    private String getSeleniumServerUrl() {
        return String.format("http://%s:%s%s",
                app.getProperty(Property.SELENIUM_SERVER_HOST),
                app.getProperty(Property.SELENIUM_SERVER_PORT),
                app.getProperty(Property.SELENIUM_SERVER_PATH));
    }
}
