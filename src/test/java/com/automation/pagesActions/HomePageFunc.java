package com.automation.pagesActions;

import com.automation.utils.TestUtils;
import org.openqa.selenium.By;

import static com.automation.general.Constants.barNavigation;

public class HomePageFunc extends TestUtils {


    public void selectActionFromToolBarMenu(barNavigation barName){

        switch (barName){

            case SECURITY, POLITICAL, CRIMINAL, INSIDE, ECONOMY, MAGAZINE, SPORT, PODCASTS, CULTURE, TIP12, PROGRAMS :{
                seleniumUtils.clickOnElement(pages.homePage.barNavigationBar.findElement(By.linkText(barName.value)));
                break;
            }

        }
    }
}
