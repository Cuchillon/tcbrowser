package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import com.ferick.alexander.config.Property;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class LocalBrowser extends RemoteBrowser {

    private DriverService driverService;

    public LocalBrowser(ApplicationManager app) {
        super(app);
    }

    @Override
    public void closeBrowser() {
        super.closeBrowser();
        if (driverService != null && driverService.isRunning()) {
            driverService.stop();
        }
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected URL getSeleniumServerUrl() {
        DriverService.Builder builder = (app.getProperty(Property.BROWSER_TYPE).equals(BrowserType.CHROME))
                ? new ChromeDriverService.Builder()
                : new GeckoDriverService.Builder();
        driverService = builder
                .usingDriverExecutable(new File("src/main/resources/drivers/" + getDriverPath()))
                .usingAnyFreePort()
                .build();
        try {
            driverService.start();
        } catch (IOException e) {
            throw new RuntimeException("Exception on start driver service", e);
        }

        return driverService.getUrl();
    }

    private String getDriverPath() {
        return (app.getProperty(Property.BROWSER_TYPE).equals(BrowserType.CHROME))
                ? OS.getHostOS().getChromePath()
                : OS.getHostOS().getFirefoxPath();
    }

    enum OS {
        LINUX("chromedriver_linux", "geckodriver_linux"),
        WINDOWS("chromedriver_win.exe", "geckodriver_win.exe"),
        MACOS("chromedriver_mac", "geckodriver_mac");

        private String chromePath;
        private String firefoxPath;

        static OS getHostOS() {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                return WINDOWS;
            } else if (os.contains("mac")) {
                return MACOS;
            } else {
                return LINUX;
            }
        }

        OS(String chromePath, String firefoxPath) {
            this.chromePath = chromePath;
            this.firefoxPath = firefoxPath;
        }

        String getChromePath() {
            return chromePath;
        }

        String getFirefoxPath() {
            return firefoxPath;
        }
    }
}
