package com.ferick.alexander.config;

import org.apache.commons.configuration2.CombinedConfiguration;

public class Config {

    public static final String UI_BASE_URL;
    public static final String SELENIUM_SERVER_HOST;
    public static final String SELENIUM_SERVER_PORT;
    public static final String SELENIUM_SERVER_PATH;
    public static final String TEST_ENV_TYPE;
    public static final String BROWSER_TYPE;
    public static final String VNC_RECORD_MODE;
    public static final String VNC_RECORD_PATH;

    static {
        CombinedConfiguration configuration = ConfigLoader.getConfiguration();

        UI_BASE_URL = configuration.getString(Property.UI_BASE_URL.getPropertyName(), "http://localhost");
        SELENIUM_SERVER_HOST = configuration.getString(Property.SELENIUM_SERVER_HOST.getPropertyName(), "localhost");
        SELENIUM_SERVER_PORT = configuration.getString(Property.SELENIUM_SERVER_PORT.getPropertyName(), "4444");
        SELENIUM_SERVER_PATH = configuration.getString(Property.SELENIUM_SERVER_PATH.getPropertyName(), "/wd/hub");
        TEST_ENV_TYPE = configuration.getString(Property.TEST_ENV_TYPE.getPropertyName(), "test-containers");
        BROWSER_TYPE = configuration.getString(Property.BROWSER_TYPE.getPropertyName(), "chrome");;
        VNC_RECORD_MODE = configuration.getString(Property.VNC_RECORD_MODE.getPropertyName(), "skip");
        VNC_RECORD_PATH = configuration.getString(Property.VNC_RECORD_PATH.getPropertyName(), "./build/");
    }
}
