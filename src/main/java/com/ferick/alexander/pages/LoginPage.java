package com.ferick.alexander.pages;

import com.ferick.alexander.elements.Button;
import com.ferick.alexander.elements.Input;
import com.ferick.alexander.elements.Label;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page(path = "/login/")
public class LoginPage extends AbstractPage {

    @FindBy(id = "auth")
    private Input authField;

    @FindBy(id = "password")
    private Input passwordField;

    @FindBy(id = "elSignIn_submit")
    private Button signInButton;

    @FindBy(xpath = "//*[@id='ipsLayout_mainArea']//*[contains(@class,'ipsMessage_error')]")
    private Label errorMessageForm;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened() {
        return signInButton.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessageForm.getText();
    }

    @Override
    protected void setLoginData(String auth, String password) {
        authField.setText(auth);
        passwordField.setText(password);
        signInButton.click();
    }
}
