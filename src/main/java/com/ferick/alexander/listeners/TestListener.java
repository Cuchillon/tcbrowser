package com.ferick.alexander.listeners;

import com.ferick.alexander.browsers.Browser;
import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        //
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Browser browser =
                (Browser) result.getTestContext().getAttribute("browser");
        saveScreenshot(browser.takeScreenshot());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //
    }

    @Override
    public void onStart(ITestContext context) {
        //
    }

    @Override
    public void onFinish(ITestContext context) {
        //
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
