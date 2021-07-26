package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    @FindBy(id = "tns1")
    public WebElement btnWeather;

    @FindBy(xpath = "//*/span[@class = \"date\"]")
    public WebElement txtDate;

    @FindBy(xpath = "//*/nav/ul")
    public WebElement barNavigationBar;
}
