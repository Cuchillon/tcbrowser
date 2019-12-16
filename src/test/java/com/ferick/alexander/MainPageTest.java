package com.ferick.alexander;

import com.ferick.alexander.browsers.Browser;
import com.ferick.alexander.pages.MainPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class MainPageTest extends TestBase {

    private Browser browser;

    @BeforeClass
    public void setUp() {
        browser = app.browser();
    }

    @Test(description = "Testing MainPage is open")
    public void mainPageOpenTest() {
        MainPage mainPage = browser.openPage(MainPage.class);
        assertTrue(mainPage.isOpened());
    }

    @AfterClass
    public void tearDown() {
        browser.closeBrowser();
    }
}