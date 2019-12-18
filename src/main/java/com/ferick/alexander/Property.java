package com.ferick.alexander;

public enum Property {

    UI_BASE_URL("ui.base.url"),
    VNC_RECORD_PATH("vnc.record.path"),
    SELENIUM_SERVER_HOST("selenium.server.host"),
    SELENIUM_SERVER_PORT("selenium.server.port"),
    SELENIUM_SERVER_PATH("selenium.server.path");

    private String propertyName;

    Property(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
