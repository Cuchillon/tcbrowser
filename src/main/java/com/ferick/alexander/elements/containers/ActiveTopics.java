package com.ferick.alexander.elements.containers;

import com.ferick.alexander.elements.ElementList;
import com.ferick.alexander.elements.singles.LinkButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActiveTopics extends Container {

    @FindBy(xpath = ".//ul[contains(@class,'ipsDataList')]/li[@class='ipsDataItem ']")
    private ElementList<DataItem> dataList;

    public static class DataItem extends Container {

        @FindBy(xpath = "./*[@class='ipsDataItem_main']/a")
        private LinkButton linkButton;

        public DataItem(WebDriver driver, WebElement element) {
            super(driver, element);
        }

        public LinkButton getLinkButton() {
            return linkButton;
        }
    }

    public ActiveTopics(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public ElementList<DataItem> getDataList() {
        return dataList;
    }
}
