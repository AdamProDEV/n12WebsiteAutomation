package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PromotionPage extends BasePage {
    public PromotionPage(WebDriver webDriver){
        super(webDriver);
    }

    @FindBy(xpath = "//*[@id=\"dclk_overlay_1892131985\"]/img")
    public WebElement popupPromotion;
}
