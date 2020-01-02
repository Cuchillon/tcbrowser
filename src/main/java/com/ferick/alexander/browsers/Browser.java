package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;
import com.ferick.alexander.pages.AbstractPage;
import com.ferick.alexander.pages.Page;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.BrowserType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public abstract class Browser {

    private WebDriver driver;
    protected ApplicationManager app;

    public Browser(ApplicationManager app) {
        this.app = app;
        configureWebDriver();
    }

    public <T extends AbstractPage> T openPage(Class<T> pageClass) {
        String path = getPagePath(pageClass);
        return openPage(pageClass, path);
    }

    public <T extends AbstractPage> T openPage(Class<T> pageClass, String path) {
        driver.get(app.getProperty(Property.UI_BASE_URL) + path);
        return AbstractPage.createPageInstance(pageClass, driver);
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public void clearCache() {
        driver.manage().deleteAllCookies();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public abstract void closeBrowser();

    protected abstract WebDriver createWebDriver();

    protected WebDriver getDriver() {
        return driver;
    }

    protected Capabilities getCapabilities() {
        return (app.getProperty(Property.BROWSER_TYPE).equals(BrowserType.CHROME))
                ? getChromeOptions()
                : getFirefoxOptions();
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("intl.accept_languages", "en");
        options.setExperimentalOption("prefs", preferences);
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "en");
        options.setProfile(profile);
        return options;
    }

    private void configureWebDriver() {
        driver = createWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    private <T extends AbstractPage> String getPagePath(Class<T> pageClass) {
        Optional<Page> annotation = Optional.ofNullable(pageClass.getAnnotation(Page.class));
        if (annotation.isPresent()) {
            return annotation.get().path();
        } else {
            throw new RuntimeException(pageClass.getCanonicalName() + " does not have Page annotation");
        }
    }
}
