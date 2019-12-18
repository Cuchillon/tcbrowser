package com.ferick.alexander;

import com.ferick.alexander.listeners.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class TestBase {

    protected final Logger log = LoggerFactory.getLogger(TestBase.class);

    protected ApplicationManager app;

    public TestBase() {
        app = new ApplicationManager();
    }

    @BeforeSuite
    public void globalSetUp(ITestContext context) {
        context.setAttribute("app", app);
    }

    @BeforeMethod(alwaysRun = true)
    public void printTestName(Method method) {
        log.info("---------------------------------------");
        log.info("TEST {}.{} started.", method.getDeclaringClass(), method.getName());

        if (!method.getAnnotation(Test.class).description().equals("")) {
            log.info("DESCRIPTION: {}", method.getAnnotation(Test.class).description());
        }
        log.info("---------------------------------------");
    }

    @AfterMethod(alwaysRun = true)
    public void printTestResult(Method method, ITestResult result) {
        log.info("---------------------------------------");
        log.info("{}.{} finished with RESULT {}", method.getDeclaringClass(), method.getName(), result.getStatus());
        log.info("---------------------------------------");
    }
}
