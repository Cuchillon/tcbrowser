package com.ferick.alexander;

import com.ferick.alexander.pages.entrance.MainPage;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;

public class ActiveTopicsTest extends TestBase {

    private static final int ACTIVE_TOPIC_NUMBER = 0;

    @Test(description = "Active topics are open")
    public void activeTopicsOpenTest() {
        log.info("Open site main page");
        MainPage mainPage = browser.openPage(MainPage.class);

        log.info("Check active topics are open");
        assertTrue(mainPage.getActiveTopicsSize() > 0, "Active topics are not open");
    }

    @Test(description = "Active topic lets pass to its content")
    public void passToActiveTopicContentTest() {
        log.info("Open site main page and pass to active topic content");
        MainPage mainPage = browser.openPage(MainPage.class);
        String activeTopicContent = mainPage.getActiveTopicContent(ACTIVE_TOPIC_NUMBER);
        mainPage.passToTopicContent(ACTIVE_TOPIC_NUMBER);

        log.info("Check topic content is open");
        Matcher matcher = Pattern.compile(".*comment=\\d+$").matcher(activeTopicContent);
        String result = "";
        while (matcher.find()) {
            String matchedString = matcher.group();
            result = matchedString.replaceFirst(".*comment=", "");
        }
        assertTrue(browser.getCurrentUrl().endsWith(result), "Topic content is not open");
    }
}
