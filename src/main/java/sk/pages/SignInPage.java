package sk.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import sk.config.WebDriverDecorator;
import sk.models.User;
import sk.pages.components.MainMenu;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SignInPage extends AbstractPage {
    private static final String pageAddress = "#/login";

    By title = By.cssSelector("h1");
    By userField = By.cssSelector("input[type='text']");
    By passwordField = By.cssSelector("input[type='password']");
    By signInButton = By.cssSelector("button[type='submit']");

    public SignInPage(WebDriverDecorator webDriver) {
        super(webDriver);
    }

    public SignInPage verifyPageIsOpen() {
        assertThat("Wrong page title", driver.getElementText(title), equalTo("Sign in"));
        return this;
    }

    public void clickSignInButton() {
        driver.clickOn(signInButton);
    }

    @Step("Fill all mandatory field for User")
    public SignInPage fillUser(String userName, String password) {
        driver.cleanAndType(userField, userName);
        driver.cleanAndType(passwordField, password);
        return this;
    }
}
