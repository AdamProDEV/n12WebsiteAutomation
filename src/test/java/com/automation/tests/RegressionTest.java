package com.automation.tests;

import com.automation.general.Constants;
import com.automation.utils.TestUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegressionTest extends TestUtils {

    @Test
    @Order(1)
    @DisplayName("Test 1: Verify menu bar functionality")
    public void navigationTest() { //todo collect all page titles

        _step("Run in loop over all enum menu names and validate title text ");
        for (Constants.barNavigation navigation : Constants.barNavigation.values()) {
            allPromotionPopupIfAppeared();

            _step("Select category");
            pageActions.homePageFunc.selectActionFromToolBarMenu(navigation);

            _step("verify title");
            seleniumUtils.verifyElementIsVisible(pages.genericPage.txtTile);
            seleniumUtils.softAssert(pages.genericPage.txtTile.getText().equals(navigation.value),
                    "Verify title text equals to: " + navigation.value);

            _step("Scroll down to verify navigation bar stay visible");
            seleniumUtils.scrollDownByPixels(1000);

            _step("Verify navigation bar still visible");
            seleniumUtils.verifyElementIsClickable(pages.homePage.barNavigationBar.findElement(By.linkText(navigation.value)));

            _step("Scroll up");
            seleniumUtils.scrollDownByPixels(-1000);
            waitSeconds(1);

        }
    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Verify date in header")
    public void dateInHeaderTest() {

        seleniumUtils.verifyElementIsVisible(pages.homePage.txtDate);
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd.MM.yy EEEE", new Locale("he"));
        String dateFromSite = pages.homePage.txtDate.getText().trim();

        _step("Compare date on the web site to today");
        String dateNow = formatter.format(new Date());
        String weekDay = dateNow.split("יום ")[1].trim();
        dateNow = weekDay + " " + dateNow.split(" ")[0];
        seleniumUtils.hardAssert(dateFromSite.equals(dateNow), dateFromSite + " have to be equals to today: " + dateNow);

    }

    @Test
    @Order(3)
    @DisplayName("Test 3: Verify weather module")
    public void weatherModuleTest() {

        _step("Click on weather button");
        seleniumUtils.clickOnElement(pages.homePage.btnWeather);
        seleniumUtils.hardAssert(pages.genericPage.txtTile.getText().equals("מזג האוויר"),
                "Page title have to be: מזג האוויר" );
        _step("Scroll to weather board");
        seleniumUtils.scrollToElement(pages.weatherPage.txtForecastBoardTitle);

        _step("Click on every weather flag and very navigate to right page (check title)");
        String[] cities = {"חיפה", "תל אביב - יפו", "אשדוד", "באר שבע", "אילת", "ירושלים", "טבריה", "צפת" };
        for(String city : cities){
            WebElement element = seleniumUtils.isElementClickable(By.xpath("//*/strong[text() = \"" + city + "\"]"), 5);
            if(element != null){
                seleniumUtils.clickOnElement(element);
                seleniumUtils.verifyElementIsVisible(pages.weatherPage.txtCityTile);
                seleniumUtils.softAssert(pages.weatherPage.txtCityTile.getText().equals(city),
                        city + "have to be presented in title");
            }else {
                seleniumUtils.softAssert(false, "element " + city + " not found");
            }
        }
    }
}


