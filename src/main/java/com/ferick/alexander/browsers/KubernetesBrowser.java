package com.ferick.alexander.browsers;

import com.ferick.alexander.ApplicationManager;
import org.openqa.selenium.WebDriver;

public class KubernetesBrowser extends Browser {

    public KubernetesBrowser(ApplicationManager app) {
        super(app);
    }

    @Override
    protected WebDriver createWebDriver(String browserType) {
        return null;
    }

    @Override
    public void closeDriver() {
        getDriver().close();
    }
}
