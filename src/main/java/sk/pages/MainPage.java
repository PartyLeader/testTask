package sk.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import sk.config.WebDriverDecorator;

import java.util.List;

public class MainPage extends AbstractPage {
    private static final String pageAddress = "/ref=nav_logo";

    private By sectorNames = By.cssSelector("#main-content .as-title-block h2 span");

    public MainPage(WebDriverDecorator webDriver) {
        this.driver = webDriver;
    }

    public void open() {
        open(pageAddress);
    }

    public List<String> getSectorNames() {
        return driver.getElementsText(sectorNames);
    }

    public void checkSectorNames(List<String> expectedNames) {
        Assert.assertEqualsNoOrder(expectedNames.toArray(), getSectorNames().toArray());
    }
}
