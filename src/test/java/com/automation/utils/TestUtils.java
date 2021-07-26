package com.automation.utils;

import com.automation.base.TestBase;
import com.automation.context.PageActions;
import com.automation.context.Pages;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class TestUtils extends TestBase {

    public static Pages pages;
    public static PageActions pageActions;

    @BeforeEach
    public void init(){
        pages = new Pages(seleniumUtils.getWebDriver()); //init pages
        pageActions = new PageActions(); // init page's functionalities and flows
        seleniumUtils.openWebPage("https://www.n12.co.il/");
    }

    public JsonNode getJsonNode(File jsonPath){
        try{
            return (new ObjectMapper()).readTree(jsonPath);
        }catch (Exception e){
            seleniumUtils.printToLog(Status.INFO, "Failed parse Json, " + e);
            return  null;
        }
    }

    public void _step(String step){
        seleniumUtils.printToLog(Status.PASS, "<b>Step: </b>" + step + ". URL: " + seleniumUtils.getWebDriver().getCurrentUrl());
    }

    public void waitSeconds(int seconds){

        try{
            Thread.sleep(seconds * 1000L); // convert to milliseconds
            seleniumUtils.printToLog(Status.PASS, "Wait " + seconds + " seconds successful");
        }catch (Exception ex){
            seleniumUtils.printToLog(Status.WARNING, "Wait" + seconds + " failed: + " + ex);
        }
    }

    public void allPromotionPopupIfAppeared(){

        if(seleniumUtils.isElementClickable(pages.promotionPage.popupPromotion, 3)){

            seleniumUtils.clickOnElement(pages.promotionPage.popupPromotion);
        }
    }


}
