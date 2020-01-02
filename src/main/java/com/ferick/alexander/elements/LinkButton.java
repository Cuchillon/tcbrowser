package com.ferick.alexander.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkButton extends Button {

    public LinkButton(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getLink() {
        return getAttribute("href");
    }
}
