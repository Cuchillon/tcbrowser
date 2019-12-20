package com.ferick.alexander.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page(path = "/login/")
public class LoginPage extends AbstractPage {

    @FindBy(id = "auth")
    private WebElement authField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "elSignIn_submit")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='ipsLayout_mainArea']//*[contains(@class,'ipsMessage_error')]")
    private WebElement errorMessageForm;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened() {
        return elementWait.isElementDisplayed(signInButton);
    }

    public String getErrorMessage() {
        return errorMessageForm.getText();
    }

    @Override
    protected void setLoginData(String auth, String password) {
        setTextToField(authField, auth);
        setTextToField(passwordField, password);
        signInButton.click();
    }
}
