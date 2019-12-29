package com.ferick.alexander.pages;

import com.ferick.alexander.elements.Button;
import com.ferick.alexander.elements.Label;
import com.ferick.alexander.elements.UserSignInMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page(path = "/")
public class MainPage extends AbstractPage {

    @FindBy(id = "elRegisterButton")
    private Button registerButton;

    @FindBy(id = "elUserSignIn")
    private Button userSignIn;

    @FindBy(id = "elUserLink")
    private Button userLink;

    @FindBy(xpath = "//*[@id='ipsLayout_mainArea']//*[@class='ipsType_pageTitle']")
    private Label startForm;

    @FindBy(id = "elUserSignIn_menu")
    private UserSignInMenu userSignInMenu;

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
        userSignInMenu.login(auth, password);
    }
}
