package com.ferick.alexander.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementWait {

    private static final long DEFAULT_TIMEOUT = 10;
    private WebDriverWait wait;

    public ElementWait(WebDriver driver) {
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public boolean isElementDisplayed(WebElement element) {
        return wait.until(driver -> element.isDisplayed());
    }
}
