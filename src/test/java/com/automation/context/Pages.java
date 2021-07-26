package com.automation.context;

import com.automation.pages.*;
import org.openqa.selenium.WebDriver;

public class Pages {

    public HomePage homePage;
    public GenericPage genericPage;
    public PromotionPage promotionPage;
    public WeatherPage weatherPage;

    public Pages (WebDriver webDriver){

        homePage = new HomePage(webDriver);
        genericPage = new GenericPage(webDriver);
        promotionPage = new PromotionPage(webDriver);
        weatherPage = new WeatherPage(webDriver);

    }
}
