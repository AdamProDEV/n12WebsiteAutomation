package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeatherPage extends BasePage {

    public WeatherPage(WebDriver webDriver){
        super(webDriver);
    }

    @FindBy(xpath = "//*/main/div[2]/h2")
    public WebElement txtForecastBoardTitle;

    @FindBy(xpath = "//*[@id=\"neoLocalForcast\"]/button/span[2]")
    public WebElement txtCityTile;



}
