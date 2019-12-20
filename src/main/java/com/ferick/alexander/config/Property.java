package com.ferick.alexander.config;

public enum Property {

    UI_BASE_URL("ui.base.url"),
    SELENIUM_SERVER_HOST("selenium.server.host"),
    SELENIUM_SERVER_PORT("selenium.server.port"),
    SELENIUM_SERVER_PATH("selenium.server.path"),
    TEST_ENV_TYPE("test.env.type"),
    BROWSER_TYPE("browser.type"),
    VNC_RECORD_MODE("vnc.record.mode"),
    VNC_RECORD_PATH("vnc.record.path"),
    TEST_USER_AUTH("test.user.auth"),
    TEST_USER_PASSWORD("test.user.password");

    private String propertyName;

    Property(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
