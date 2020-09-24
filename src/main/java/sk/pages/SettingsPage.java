package sk.pages;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SettingsPage extends AbstractPage {
    private static final String pageAddress = "#/settings";

    By title = By.cssSelector("h1");

    //todo create fields
    public SettingsPage(WebDriverDecorator webDriver) {
        super(webDriver);
    }

    public SettingsPage verifyPageIsOpen() {
        assertThat("Wrong page title", driver.getElementText(title), equalTo("Your Settings"));
        return this;
    }
}
