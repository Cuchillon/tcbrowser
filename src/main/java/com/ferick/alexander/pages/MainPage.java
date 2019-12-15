package com.ferick.alexander.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    public static final String PATH = "/";

    @FindBy(id = "elRegisterButton")
    private WebElement registerButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isOpened() {
        return elementWait.isElementDisplayed(registerButton);
    }
}
