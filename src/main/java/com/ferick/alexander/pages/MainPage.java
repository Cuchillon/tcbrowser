package com.ferick.alexander.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Page(path = "/")
public class MainPage extends AbstractPage {

    @FindBy(id = "elRegisterButton")
    private WebElement registerButton;

    @FindBy(id = "elUserSignIn")
    private WebElement userSignIn;

    @FindBy(name = "auth")
    private WebElement authField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(id = "elSignIn_submit")
    private WebElement signInButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened() {
        return elementWait.isElementDisplayed(registerButton);
    }

    public LoginPage login(String auth, String password) {
        userSignIn.click();
        setTextToField(authField, auth);
        setTextToField(passwordField, password);
        signInButton.click();
        return createPageInstance(LoginPage.class, driver);
    }
}
