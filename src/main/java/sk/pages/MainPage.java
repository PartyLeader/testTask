package sk.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import sk.config.WebDriverDecorator;
import sk.pages.components.Sector;

import java.util.List;

public class MainPage extends AbstractPage {
    private static final String pageAddress = "/ref=nav_logo";

    private Sector sector;

    private By sectorNames = By.cssSelector("#main-content .as-title-block h2 span");

    public MainPage(WebDriverDecorator webDriver) {
        this.driver = webDriver;
    }

    @Step("Open Main page")
    public void open() {
        open(pageAddress);
    }

    public List<String> getSectorNames() {
        return driver.getElementsText(sectorNames);
    }

    @Step("Verify that presented correct sectors")
    public void checkSectorNames(List<String> expectedNames) {
        Assert.assertEqualsNoOrder(expectedNames.toArray(), getSectorNames().toArray());
    }
    @Step("Start work with sector {sectorName}")
    public Sector getSector(String sectorName) {
        return new Sector(driver, sectorName);
    }
    @Step("Start work with sector {sectorName}")
    public Sector getSectorByIndex(int index) {
        return new Sector(driver, index);
    }
}
