package sk.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import sk.config.WebDriverDecorator;
import sk.steps.CartPageSteps;
import sk.steps.DetailPageSteps;
import sk.steps.MainPageSteps;

import java.util.Objects;


public class BaseTest {
    private WebDriverDecorator driver = new WebDriverDecorator();
    DetailPageSteps detailsPageSteps;
    CartPageSteps cartPageSteps;

    MainPageSteps mainPageSteps;

    protected WebDriverDecorator getDriver() {
        return this.driver;
    }

    @BeforeClass(alwaysRun = true)
    public void initPages() {
        mainPageSteps = new MainPageSteps(getDriver());
        detailsPageSteps = new DetailPageSteps(getDriver());
        cartPageSteps = new CartPageSteps(getDriver());
    }

    @BeforeMethod(alwaysRun = true)
    public void openMainPage() {
        Objects.requireNonNull(mainPageSteps).open();
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        getDriver().quit();
    }
}
