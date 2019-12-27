package com.ferick.alexander;

import com.ferick.alexander.browsers.Browser;
import com.ferick.alexander.browsers.BrowserFactory;
import com.ferick.alexander.config.ConfigLoader;
import com.ferick.alexander.config.Property;
import org.apache.commons.configuration2.CombinedConfiguration;

public class ApplicationManager {

    private CombinedConfiguration config;
    private Browser browser;

    public ApplicationManager() {
        config = new ConfigLoader().getConfiguration();
    }

    public String getProperty(Property property) {
        return config.getString(property.getPropertyName());
    }

    public Browser browser(String browserType) {
        if (browser == null) {
            browser = new BrowserFactory(this).getBrowser(browserType);
        }
        return browser;
    }
}
