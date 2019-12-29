package com.ferick.alexander.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public Button(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public void click() {
        element.click();
    }
}
