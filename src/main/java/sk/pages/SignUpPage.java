package sk.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SignUpPage extends AbstractPage {
    private static final String pageAddress = "#/register";

    By title = By.cssSelector("h1");
    By userField = By.cssSelector("input[placeholder='Username']");
    By passwordField = By.cssSelector("input[type='password']");
    By mailField = By.cssSelector("input[placeholder='Email']");
    By signUpButton = By.cssSelector("button[type='submit']");

    public SignUpPage(WebDriverDecorator webDriver) {
        super(webDriver);
    }

    public SignUpPage verifyPageIsOpen() {
        assertThat("Wrong page title", driver.getElementText(title), equalTo("Sign up"));
        return this;
    }

    public void clickSignUpButton() {
        driver.clickOn(signUpButton);
    }

    @Step("Fill all mandatory fields for new User")
    public SignUpPage fillUser(String userName, String password, String mail) {
        driver.cleanAndType(userField, userName);
        driver.cleanAndType(mailField, mail);
        driver.cleanAndType(passwordField, password);
        return this;
    }
}
