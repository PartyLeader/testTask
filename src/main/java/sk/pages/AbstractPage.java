package sk.pages;

import sk.config.WebDriverDecorator;

public abstract class AbstractPage implements Page {
    protected String uiBaseUrl = "https://www.amazon.com";

    protected WebDriverDecorator driver;

    public void open(String pageURL) {
        driver.navigate().to(uiBaseUrl + pageURL);
    }

    @Override
    public void open() {
        driver.navigate().to(uiBaseUrl);
    }
}
