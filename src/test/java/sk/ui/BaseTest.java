package sk.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import sk.config.AbstractTest;
import sk.steps.MainPageSteps;

import java.util.Objects;

import static sk.constants.UserConstants.SERVICE_USER;

public class BaseTest extends AbstractTest {

    protected MainPageSteps mainPageSteps;

    @BeforeClass(alwaysRun = true)
    public void initPages() {
        mainPageSteps = new MainPageSteps(getDriver());
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
