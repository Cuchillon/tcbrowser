package com.ferick.alexander.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(id = "elUserLink")
    private WebElement userLink;

    @FindBy(xpath = "//*[@id='ipsLayout_mainArea']//*[@class='ipsType_pageTitle']")
    private WebElement startForm;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened() {
        return elementWait.isElementDisplayed(startForm);
    }

    public boolean isUserLoggedIn() {
        return elementWait.isElementDisplayed(userLink);
    }

    @Override
    protected void setLoginData(String auth, String password) {
        userSignIn.click();
        setTextToField(authField, auth);
        setTextToField(passwordField, password);
        signInButton.click();
    }
}
