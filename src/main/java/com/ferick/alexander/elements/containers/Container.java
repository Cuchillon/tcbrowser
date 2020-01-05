package com.ferick.alexander.elements.containers;

import com.ferick.alexander.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Container extends BaseElement {

    public Container(WebDriver driver, WebElement element) {
        super(driver, element);
    }
}
