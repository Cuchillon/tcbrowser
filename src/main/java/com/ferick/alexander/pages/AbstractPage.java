package com.ferick.alexander.pages;

import com.ferick.alexander.elements.ElementFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;

public abstract class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new ElementFieldDecorator(driver), this);
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
}
