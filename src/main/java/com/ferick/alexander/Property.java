package com.ferick.alexander;

public enum Property {

    UI_BASE_URL("ui.base.url");

    private String propertyName;

    Property(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
