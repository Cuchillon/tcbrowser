package com.ferick.alexander;

import com.ferick.alexander.listeners.TestListener;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class TestBase {

    protected ApplicationManager app;

    public TestBase() {
        app = new ApplicationManager();
    }

    @BeforeSuite
    public void globalSetUp(ITestContext context) {
        context.setAttribute("app", app);
    }
}
