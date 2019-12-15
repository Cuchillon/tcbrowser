package com.ferick.alexander.pages;

import com.ferick.alexander.utils.ElementWait;
import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;
    protected ElementWait elementWait;

    public Page(WebDriver driver) {
        this.driver = driver;
        elementWait = new ElementWait(driver);
    }

    public abstract boolean isOpened();
}
