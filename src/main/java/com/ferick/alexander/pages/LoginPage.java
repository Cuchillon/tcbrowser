package com.ferick.alexander.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public MainPage login(String auth, String password) {
        setLoginData(auth, password);
        return createPageInstance(MainPage.class, driver);
    }

    public LoginPage incorrectLogin(String auth, String password) {
        setLoginData(auth, password);
        return createPageInstance(LoginPage.class, driver);
    }

    public String getErrorMessage() {
        return errorMessageForm.getText();
    }

    private void setLoginData(String auth, String password) {
        setTextToField(authField, auth);
        setTextToField(passwordField, password);
        signInButton.click();
    }
}
