package com.ferick.alexander;

import com.ferick.alexander.config.Property;
import com.ferick.alexander.pages.entrance.LoginPage;
import com.ferick.alexander.pages.entrance.MainPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SiteLoginTest extends TestBase {

    private static final String LOGIN_ERROR_MESSAGE_RUS = "Отображаемое имя или email адрес, введённый вами, " +
            "не принадлежит ни к одному аккаунту. Убедитесь в правильности введённой информации.";
    private static final String LOGIN_ERROR_MESSAGE_ENG = "The display name or password was incorrect. " +
            "Please try again (make sure your caps lock is off).";
    private static final String PASSWORD_ERROR_MESSAGE_RUS = "Введенный вами пароль недействителен. Пожалуйста, " +
            "попробуйте еще раз (убедитесь, что режим Caps Lock выключен).";
    private static final String WRONG_AUTH = "wrong_auth";
    private static final String WRONG_PASSWORD = "wrong_password";

    @Test(description = "Testing MainPage is open")
    public void mainPageOpenTest() {
        log.info("Open site main page");
        MainPage mainPage = browser.openPage(MainPage.class);

        log.info("Check site main page is open");
        assertTrue(mainPage.isOpened(), "MainPage is not open");
    }

    @Test(description = "Testing successful login from MainPage")
    public void correctLoginFromMainPageTest() {
        log.info("Open site main page and log in with correct user data");
        MainPage mainPage = browser.openPage(MainPage.class);
        mainPage = mainPage.login(
                app.getProperty(Property.TEST_USER_AUTH),
                app.getProperty(Property.TEST_USER_PASSWORD));

        log.info("Check it returns to main page anew and user logged in successfully");
        assertTrue(mainPage.isOpened(), "MainPage is not open anew");
        assertTrue(mainPage.isUserLoggedIn(), "User did not log in successfully");
    }

    @Test(description = "Testing successful login from LoginPage")
    public void correctLoginFromLoginPageTest() {
        log.info("Open site login page and log in with correct user data");
        LoginPage loginPage = browser.openPage(LoginPage.class);
        MainPage mainPage = loginPage.login(
                app.getProperty(Property.TEST_USER_AUTH),
                app.getProperty(Property.TEST_USER_PASSWORD));

        log.info("Check it passes to main page and user logged in successfully");
        assertTrue(mainPage.isOpened(), "MainPage is not open anew");
        assertTrue(mainPage.isUserLoggedIn(), "User did not log in successfully");
    }

    @Test(description = "Testing incorrect login from MainPage")
    public void incorrectLoginFromMainPageTest() {
        log.info("Open site main page and log in with incorrect user data");
        MainPage mainPage = browser.openPage(MainPage.class);
        LoginPage loginPage = mainPage.incorrectLogin(WRONG_AUTH, WRONG_PASSWORD);

        log.info("Check login page is open and error message is displayed");
        assertTrue(loginPage.isOpened(), "LoginPage is not open");
        assertEquals(loginPage.getErrorMessage(), LOGIN_ERROR_MESSAGE_ENG, "Error message is not correct");
    }

    @Test(description = "Testing incorrect login from LoginPage", dataProvider = "Login data")
    public void incorrectLoginFromLoginPageTest(String auth, String password) {
        log.info("Open login page and log in with incorrect user data");
        LoginPage loginPage = browser.openPage(LoginPage.class);
        LoginPage returnedLoginPage = loginPage.incorrectLogin(auth, password);

        log.info("Check it returns to login page anew and error message is displayed");
        assertTrue(returnedLoginPage.isOpened(), "LoginPage is not open anew");
        assertEquals(loginPage.getErrorMessage(), LOGIN_ERROR_MESSAGE_ENG, "Error message is not correct");
    }

    @DataProvider(name = "Login data")
    private Object[][] setLoginData() {
        return new Object[][] {
                {WRONG_AUTH, WRONG_PASSWORD},
                {app.getProperty(Property.TEST_USER_AUTH), WRONG_PASSWORD},
                {WRONG_AUTH, app.getProperty(Property.TEST_USER_PASSWORD)},
                {app.getProperty(Property.TEST_USER_AUTH), ""},
                {"", app.getProperty(Property.TEST_USER_PASSWORD)},
                {"", ""}
        };
    }
}