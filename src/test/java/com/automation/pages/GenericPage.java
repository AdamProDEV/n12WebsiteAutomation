package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GenericPage extends BasePage {

    public GenericPage(WebDriver webDriver){
        super(webDriver);
    }

    @FindBy(xpath = "//*/header/h1")
    public WebElement txtTile;
}
