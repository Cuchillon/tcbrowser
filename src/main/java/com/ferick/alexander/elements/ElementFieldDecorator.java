package com.ferick.alexander.elements;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
        if (field.getAnnotation(FindBy.class) == null &&
                field.getAnnotation(FindBys.class) == null &&
                field.getAnnotation(FindAll.class) == null) {
            return null;
        }
        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }
        if (isBaseElementList(field)) {
            return decorateElementList(loader, field, locator);
        } else if (BaseElement.class.isAssignableFrom(field.getType())) {
            return decorateElement(loader, field, locator);
        }
        return super.decorate(loader, field);
    }

    private boolean isBaseElementList(Field field) {
        if (List.class.isAssignableFrom(field.getType())) {
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return false;
            }
            Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
            return BaseElement.class.isAssignableFrom((Class<?>) listType);
        }
        return false;
    }

    private Object decorateElement(final ClassLoader loader, final Field field, ElementLocator locator) {
        final WebElement webElement = proxyForLocator(loader, locator);
        Object instance = createInstance(field.getType(), webElement);
        initInstanceElements(webElement, instance);
        return instance;
    }

    private Object decorateElementList(final ClassLoader loader, final Field field, ElementLocator locator) {
        final List<WebElement> webElements = proxyForListLocator(loader, locator);
        List<BaseElement> elementList = new ElementList<>();
        Type listType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        for (WebElement webElement : webElements) {
            Object instance = createInstance((Class<?>) listType, webElement);
            initInstanceElements(webElement, instance);
            elementList.add((BaseElement) instance);
        }
        return elementList;
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

    private void initInstanceElements(WebElement webElement, Object instance) {
        if (Container.class.isAssignableFrom(instance.getClass())) {
            PageFactory.initElements(new ElementFieldDecorator(webElement, driver), instance);
        }
    }
}
