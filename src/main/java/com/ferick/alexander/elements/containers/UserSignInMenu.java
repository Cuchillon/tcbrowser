package com.ferick.alexander.elements.containers;

import com.ferick.alexander.elements.singles.Button;
import com.ferick.alexander.elements.singles.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserSignInMenu extends Container {

    @FindBy(name = "auth")
    private Input authField;

    @FindBy(name = "password")
    private Input passwordField;

    @FindBy(id = "elSignIn_submit")
    private Button signInButton;

    public UserSignInMenu(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public void login(String auth, String password) {
        authField.setText(auth);
        passwordField.setText(password);
        signInButton.click();
    }
}
