package sk.pages;

import org.apache.log4j.Logger;
import sk.config.WebDriverDecorator;

public class AbstractPageComponent {
    public final Logger log = Logger.getLogger(AbstractPageComponent.class);

    protected WebDriverDecorator driver;

    public AbstractPageComponent(WebDriverDecorator driver) {
        this.driver = driver;
    }

    public void scrollToComponent() {
        try {
            driver.executeJavaScript("arguments[0].scrollIntoView(true)");
            Thread.sleep(500); //wait page rendering
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void waitForAngular() {
        try {
            driver.executeJavaScript("arguments[0].scrollIntoView(true)");
            Thread.sleep(500); //wait page rendering
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
