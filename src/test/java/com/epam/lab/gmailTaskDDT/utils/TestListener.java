package com.epam.lab.gmailTaskDDT.utils;

import org.apache.log.output.net.SocketOutputTarget;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger LOG = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOG.info(String.format("%s is started", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info(String.format("%s is passed", iTestResult.getName()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.info(String.format("%s is failed", iTestResult.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOG.info(String.format("%s is skipped", iTestResult.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOG.info(String.format("Start %s", iTestContext.getSuite().getName()));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOG.info(String.format("Finish %s", iTestContext.getSuite().getName()));
        LOG.info("Results:");
        LOG.info("Passed:");
        iTestContext.getPassedTests().getAllResults().forEach(i -> LOG.info("\t" + i.getName()));
        LOG.info("Failed:");
        iTestContext.getFailedTests().getAllResults().forEach(i -> LOG.info("\t" + i.getName()));
        LOG.info("Skipped:");
        iTestContext.getSkippedTests().getAllResults().forEach(i -> LOG.info("\t" + i.getName()));
    }
}
