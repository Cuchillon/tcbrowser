package com.ferick.alexander.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Label extends BaseElement {

    public Label(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getText() {
        return element.getText();
    }
}
