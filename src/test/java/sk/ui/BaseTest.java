package sk.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import sk.config.AbstractTest;
import sk.steps.CartPageSteps;
import sk.steps.DetailPageSteps;
import sk.steps.MainPageSteps;

import java.util.Objects;

public class BaseTest extends AbstractTest {
    DetailPageSteps detailsPageSteps;
    CartPageSteps cartPageSteps;

    MainPageSteps mainPageSteps;



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
