package com.ferick.alexander.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseElement {

    private static final long DEFAULT_TIMEOUT = 10;

    protected final WebElement element;
    protected final WebDriverWait wait;

    public BaseElement(WebDriver driver, WebElement element) {
        this.element = element;
        wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    public BaseElement withTimeout(long timeOutInSeconds) {
        wait.withTimeout(Duration.ofSeconds(timeOutInSeconds));
        return this;
    }

    public BaseElement pollingEvery(long sleepInMillis) {
        wait.pollingEvery(Duration.ofMillis(sleepInMillis));
        return this;
    }

    public boolean isDisplayed() {
        return wait.until(driver -> element.isDisplayed());
    }

    public String getText() {
        return element.getText();
    }

    public String waitAndGetText() {
        return wait.until(driver -> element.getText());
    }

    public String getAttribute(String attributeName) {
        return wait.until(driver -> element.getAttribute(attributeName));
    }
}
