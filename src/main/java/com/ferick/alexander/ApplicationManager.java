package com.ferick.alexander;

import com.ferick.alexander.browsers.Browser;
import com.ferick.alexander.browsers.BrowserFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

    private static final String PROPERTIES_FILE_NAME = "/local.properties";

    private Properties properties;
    private Browser browser;

    public ApplicationManager() {
        loadProperties();
    }

    public String getProperty(Property property) {
        return properties.getProperty(property.getPropertyName());
    }

    public Browser browser() {
        if (browser == null) {
            browser = new BrowserFactory(this).getBrowser();
        }
        return browser;
    }

    private void loadProperties() {
        properties = new Properties();

        try {
            properties.load(new FileReader(
                    ApplicationManager.class.getResource(PROPERTIES_FILE_NAME).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
