package com.automation.base;

import com.automation.base.extentReports.ExtentReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class SeleniumUtils {

    private final WebDriver webDriver;
    private JavascriptExecutor js;
    private TakesScreenshot scrShot;
    private final WebDriverWait webDriverWait;

    public SeleniumUtils(WebDriver webDriver) {
        this.webDriver = webDriver;
        js = (JavascriptExecutor) webDriver;
        scrShot = ((TakesScreenshot) webDriver);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofMillis(5000));
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void openWebPage(String webPage) {
        webDriver.get(webPage);
    }

    public WebElement verifyElementIsVisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            highLightElement(element);
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            printToLog(Status.PASS, element.toString().split("->")[1] + ", " + element.getText() +  " element is visible.");
        } catch (Exception e) {
            printToLog(Status.WARNING, element.toString() +  " element not visible. " + e);
            printToLog(Status.WARNING, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
        return element;
    }

    public WebElement verifyElementIsClickable(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            highLightElement(element);
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            printToLog(Status.PASS, element.toString().split("->")[1] + ", " + element.getText() +  " element is clickable.");
        } catch (Exception e) {
            printToLog(Status.WARNING, element.toString() + " element not clickable. " + e);
            printToLog(Status.WARNING, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        }
        return element;
    }

    public boolean isElementClickable(WebElement element, int seconds) {
        try {
            (new WebDriverWait(webDriver, Duration.ofSeconds(seconds))).until(ExpectedConditions.elementToBeClickable(element));
            highLightElement(element);
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            printToLog(Status.PASS, element.toString().split("->")[1] + ", " + element.getText() +  " element is clickable.");
            return true;
        } catch (Exception e) {
            printToLog(Status.PASS, element.toString() + ", element not clickable." + e.getMessage());
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            return false;
        }
    }

    public WebElement isElementClickable(By by, int seconds) {
        WebElement element = null;
        try {
            element =  (new WebDriverWait(webDriver, Duration.ofSeconds(seconds))).until(ExpectedConditions.elementToBeClickable(by));
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            printToLog(Status.PASS, by +  " element is clickable.");
        } catch (Exception e) {
            printToLog(Status.PASS, by + ", element not clickable." + e.getMessage());
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        return element;
    }


    public void clickOnElement(WebElement element) {
        try {
            verifyElementIsClickable(element).click();
            printToLog(Status.PASS, element.toString().split("->")[1] + " element successful clicked.");
        } catch (Exception e) {
            printToLog(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            hardAssert(false, element.toString() + " Failed click on element. " + e);
        }
    }

    public void sendTextToElement(WebElement element, String text) {
        try {
            verifyElementIsVisible(element).sendKeys(text);
            printToLog(Status.PASS, element.toString().split("->")[1] + " text: " + text + " , successful sent");
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            printToLog(Status.WARNING, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            hardAssert(false, element.toString() + " Failed to send text: " + text + " . " + e);
        }
    }

    public void scrollDownByPixels(int pixels){

        try {
            js.executeScript("window.scrollBy(0," + pixels + ")");
            printToLog(Status.PASS, "Scroll down by " + pixels + " successful done");
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }catch (Exception e){
            printToLog(Status.WARNING, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            hardAssert(false, "Scroll down by " + pixels + " failed. " + e);

        }
    }

    public void scrollToElement(WebElement element){

        try {
            js.executeScript("arguments[0].scrollIntoView();", element);
            printToLog(Status.PASS, "Scroll down to " + element + " successful done");
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }catch (Exception e){
            printToLog(Status.WARNING, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            hardAssert(false, "Scroll down to " + element + " failed. " + e);

        }
    }

    public void scrollDownAtTheBottomOfThePage(){

        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            printToLog(Status.PASS, "Scroll at the bottom of the page successful done");
            printToLog(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }catch (Exception e){
            printToLog(Status.WARNING, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            hardAssert(false, "Scroll at the bottom of the page failed" + e);

        }
    }

    public void hardAssert(boolean assertion, String description) {

        if (!assertion) {
            printToLog(Status.FAIL, description);
            Assertions.fail(description);
        } else {
            printToLog(Status.PASS, description);
        }
    }

    public void softAssert(boolean assertion, String description) {
        if (!assertion)
            printToLog(Status.WARNING, description);
        else
            printToLog(Status.PASS, description);

    }

    public void printToLog(Status status, String message) {
        ExtentReporter.logToReport(status, message);
        System.out.println(message);
    }

    public void printToLog(Status status, Media media) {
        ExtentReporter.logToReport(status, media);
        System.out.println("Log media info: " + media.getPath());
    }

    private void highLightElement(WebElement element) {

        js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid blue;');", element);
    }

    public String takeScreenshot() {

        String destination;
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            File source = scrShot.getScreenshotAs(OutputType.FILE);
            destination = "report/images/" + dateName + ".png";
            FileUtils.copyFile(source, new File(destination));
            destination = destination.split("report/")[1];
        } catch (Exception e) {
            destination = "/";
            printToLog(Status.WARNING, "Screenshot take failed.");
        }

        return destination;
    }

}
