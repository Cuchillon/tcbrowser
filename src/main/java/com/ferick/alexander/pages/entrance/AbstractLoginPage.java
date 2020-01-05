package com.ferick.alexander.pages.entrance;

import com.ferick.alexander.pages.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class AbstractLoginPage extends AbstractPage {

    public AbstractLoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(String auth, String password) {
        setLoginData(auth, password);
        return createPageInstance(MainPage.class, driver);
    }

    public LoginPage incorrectLogin(String auth, String password) {
        setLoginData(auth, password);
        return createPageInstance(LoginPage.class, driver);
    }

    protected abstract void setLoginData(String auth, String password);
}
