package sk.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sk.config.AbstractTest;
import sk.models.User;
import sk.steps.MainPageSteps;

import java.util.Objects;

import static sk.constants.UserConstants.SERVICE_USER;
import static sk.helpers.RandomHelper.*;

public class WriterTest extends BaseTest {
    protected User WRITER;

    @BeforeSuite(alwaysRun = true)
    public void createUser() {
        WRITER = new User(randomString(), randomPassword(), randomMail());
        mainPageSteps = new MainPageSteps(getDriver());
        mainPageSteps.open();
        mainPageSteps.createUser(WRITER);
    }

}
