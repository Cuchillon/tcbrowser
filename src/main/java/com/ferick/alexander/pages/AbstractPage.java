package com.ferick.alexander.pages;

import com.ferick.alexander.utils.ElementWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.util.Optional;

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

    public static <T extends AbstractPage> String getPagePath(Class<T> pageClass) {
        Optional<Page> annotation = Optional.ofNullable(pageClass.getAnnotation(Page.class));
        if (annotation.isPresent()) {
            return annotation.get().path();
        } else {
            throw new RuntimeException(pageClass.getCanonicalName() + " does not have Page annotation");
        }
    }

    public abstract boolean isOpened();

    public void setTextToField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }
}
