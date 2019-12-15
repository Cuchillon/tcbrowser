package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public abstract class Browser {

    private WebDriver driver;
    private ApplicationManager app;

    public Browser(ApplicationManager app) {
        this.app = app;
        configureWebDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        driver.close();
    }

    protected abstract WebDriver createWebDriver(String browserType);

    protected ChromeOptions getChromeOptions() {
        return new ChromeOptions();
    }

    protected FirefoxOptions getFirefoxOptions() {
        return new FirefoxOptions();
    }

    private void configureWebDriver() {
        String browserType = System.getProperty("browser", BrowserType.CHROME);
        driver = createWebDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void clearCache() {
        driver.manage().deleteAllCookies();
    }
}
