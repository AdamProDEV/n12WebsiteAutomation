package com.automation.base.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private final ExtentReports extentReports;
    private ExtentSparkReporter reporter;

    public ExtentManager(){

        extentReports = new ExtentReports();
        reporter = new ExtentSparkReporter("report/report.html");
        reporter.config().setReportName("N12 com.automation");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Adam");
    }

    public ExtentReports getExtentReports() {

        return extentReports;
    }
}
