package com.ferick.alexander;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class Browser {

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

    private void configureWebDriver() {
        String browser = System.getProperty("browser", BrowserType.CHROME);
        BrowserWebDriverContainer browserContainer;

        if (browser.equals(BrowserType.FIREFOX)) {
            browserContainer = new BrowserWebDriverContainer().withCapabilities(getFirefoxOptions());
        }
        else {
            browserContainer = new BrowserWebDriverContainer().withCapabilities(getChromeOptions());
        }

        driver = browserContainer.getWebDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void clearCache() {
        driver.manage().deleteAllCookies();
    }

    private ChromeOptions getChromeOptions() {
        return new ChromeOptions();
    }

    private FirefoxOptions getFirefoxOptions() {
        return new FirefoxOptions();
    }
}
