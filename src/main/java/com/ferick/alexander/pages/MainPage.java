package com.ferick.alexander.pages;

import com.ferick.alexander.elements.ActiveTopics;
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

    @FindBy(xpath = "//*[@id='ipsLayout_sidebar']//*[@class='ipsList_reset']")
    private ActiveTopics activeTopics;

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

    public Integer getActiveTopicsSize() {
        return activeTopics.getDataList().size();
    }

    public String getActiveTopicContent(int topicNumber) {
        return activeTopics
                .getDataList()
                .get(topicNumber)
                .getLinkButton()
                .getAttribute("href");
    }

    public void passToTopicContent(int topicNumber) {
        activeTopics.getDataList()
                .get(topicNumber)
                .getLinkButton()
                .click();
    }

    public void passToTopicContent(String topicName) {
        activeTopics.getDataList()
                .getElement(topicName)
                .ifPresent(dataItem -> dataItem.getLinkButton().click());
    }

    @Override
    protected void setLoginData(String auth, String password) {
        userSignIn.click();
        userSignInMenu.login(auth, password);
    }
}
