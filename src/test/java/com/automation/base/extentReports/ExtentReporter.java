package com.automation.base.extentReports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;

public class ExtentReporter {

    private static ExtentTest extentTest;

    public static void setExtentTest(ExtentTest extentTest){

        ExtentReporter.extentTest = extentTest;
    }

    public static void logToReport(Status status, String message){

        extentTest.log(status, message);
    }

    public static void logToReport(Status status, Media media){

        extentTest.log(status, media);
    }
}
