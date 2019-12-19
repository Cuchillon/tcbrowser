package com.ferick.alexander.pages;

import com.ferick.alexander.utils.ElementWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected ElementWait elementWait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementWait = new ElementWait(driver);
    }

    public static <T extends AbstractPage> T createPageInstance(Class<T> pageClass, WebDriver driver) {
        Constructor<T> constructor;
        T page;
        try {
            constructor = pageClass.getConstructor(WebDriver.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Constructor with WebDriver was not found", e);
        }
        try {
            page = constructor.newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Exception on creating instance of the page " +
                    pageClass.getCanonicalName(), e);
        }
        return page;
    }

    public abstract boolean isOpened();

    public void setTextToField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
