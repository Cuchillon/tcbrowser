package com.ferick.alexander;

import com.ferick.alexander.browsers.Browser;
import com.ferick.alexander.pages.MainPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MainPageTest extends TestBase {

    private Browser browser;

    @BeforeClass
    public void setUp() {
        browser = app.browser();
    }

    @Test
    public void mainPageOpenTest() {
        MainPage mainPage = browser.openPage(MainPage.class, MainPage.PATH);
        assertTrue(mainPage.isOpened());
    }
}