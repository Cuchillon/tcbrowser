package com.ferick.alexander.elements.singles;

import com.ferick.alexander.elements.BaseElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Input extends BaseElement {

    public Input(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public void setText(String text) {
        element.clear();
        element.sendKeys(text);
    }
}
