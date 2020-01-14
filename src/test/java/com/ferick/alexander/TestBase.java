package com.ferick.alexander;

import com.ferick.alexander.browsers.Browser;
import com.ferick.alexander.listeners.TestListener;
import com.ferick.alexander.tools.AllureLogger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class TestBase {

    protected final AllureLogger log = new AllureLogger(TestBase.class);

    protected ApplicationManager app;
    protected Browser browser;

    public TestBase() {
        app = new ApplicationManager();
    }

    @BeforeClass
    public void setUp(ITestContext context) {
        context.setAttribute("app", app);
        browser = app.browser();
    }

    @BeforeMethod(alwaysRun = true)
    public void printTestName(Method method) {
        log.info("---------------------------------------");
        log.info(String.format("TEST %s.%s started.", method.getDeclaringClass(), method.getName()));

        if (!method.getAnnotation(Test.class).description().equals("")) {
            log.info(String.format("DESCRIPTION: %s", method.getAnnotation(Test.class).description()));
        }
        log.info("---------------------------------------");
    }

    @AfterMethod(alwaysRun = true)
    public void printTestResult(Method method, ITestResult result) {
        log.info("---------------------------------------");
        log.info(String.format("%s.%s finished with RESULT %d",
                method.getDeclaringClass(), method.getName(), result.getStatus()));
        log.info("---------------------------------------");

        browser.clearCache();
    }

    @AfterClass
    public void tearDown(ITestContext context) {
        browser.closeBrowser();
        context.removeAttribute("app");
    }
}
