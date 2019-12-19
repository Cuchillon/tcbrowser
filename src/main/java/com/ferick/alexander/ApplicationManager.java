package com.ferick.alexander;

import com.ferick.alexander.browsers.Browser;
import com.ferick.alexander.browsers.BrowserFactory;

public class ApplicationManager {

    private Browser browser;

    public Browser browser() {
        if (browser == null) {
            browser = new BrowserFactory(this).getBrowser();
        }
        return browser;
    }
}
