package com.ferick.alexander.pages;

import com.ferick.alexander.elements.Button;
import com.ferick.alexander.elements.Input;
import com.ferick.alexander.elements.Label;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page(path = "/")
public class MainPage extends AbstractPage {

    @FindBy(id = "elRegisterButton")
    private Button registerButton;

    @FindBy(id = "elUserSignIn")
    private Button userSignIn;

    @FindBy(name = "auth")
    private Input authField;

    @FindBy(name = "password")
    private Input passwordField;

    @FindBy(id = "elSignIn_submit")
    private Button signInButton;

    @FindBy(id = "elUserLink")
    private Button userLink;

    @FindBy(xpath = "//*[@id='ipsLayout_mainArea']//*[@class='ipsType_pageTitle']")
    private Label startForm;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened() {
        return startForm.isDisplayed();
    }

    public boolean isUserLoggedIn() {
        return userLink.isDisplayed();
    }

    @Override
    protected void setLoginData(String auth, String password) {
        userSignIn.click();
        authField.setText(auth);
        passwordField.setText(password);
        signInButton.click();
    }
}
