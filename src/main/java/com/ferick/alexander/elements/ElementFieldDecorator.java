package com.ferick.alexander.elements;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ElementFieldDecorator extends DefaultFieldDecorator {

    private WebDriver driver;

    public ElementFieldDecorator(final WebDriver driver) {
        super(new DefaultElementLocatorFactory(driver));
        this.driver = driver;
    }

    private ElementFieldDecorator(final SearchContext searchContext, final WebDriver driver) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.driver = driver;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (BaseElement.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field);
        }
        return super.decorate(loader, field);
    }

    private Object decorateElement(final ClassLoader loader, final Field field) {
        final WebElement webElement = proxyForLocator(loader, createLocator(field));
        Object instance = createInstance(field.getType(), webElement);
        if (Container.class.isAssignableFrom(instance.getClass())) {
            PageFactory.initElements(new ElementFieldDecorator(webElement, driver), instance);
        }
        return instance;
    }

    private ElementLocator createLocator(final Field field) {
        return factory.createLocator(field);
    }

    private Object createInstance(Class<?> fieldType, WebElement webElement) {
        try {
            Constructor<?> constructor = fieldType.getConstructor(WebDriver.class, WebElement.class);
            return constructor.newInstance(driver, webElement);
        } catch (Exception e) {
            throw new RuntimeException("Exception on creating instance of the element " +
                    fieldType.getCanonicalName(), e);
        }
    }
}
