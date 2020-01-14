package com.ferick.alexander.elements.singles;

import com.ferick.alexander.elements.BaseElement;
import com.ferick.alexander.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public Button(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public void click() {
        element.click();
    }

    public <T extends AbstractPage> T clickAndOpenPage(Class<T> pageClass, WebDriver driver) {
        element.click();
        return AbstractPage.createPageInstance(pageClass, driver);
    }
}
