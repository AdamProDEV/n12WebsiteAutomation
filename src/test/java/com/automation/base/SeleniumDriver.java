package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SeleniumDriver {

    WebDriver webDriver;

    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chrome/chromeDriver_91.0.4472.19_win32.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        return webDriver;
    }
}
