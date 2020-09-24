package sk.config;

import org.testng.annotations.Listeners;

@Listeners({TestExecutionListener.class})
public class AbstractTest{

    protected WebDriverDecorator driver = new WebDriverDecorator();

    public WebDriverDecorator getDriver() {
        return this.driver;
    }
}
