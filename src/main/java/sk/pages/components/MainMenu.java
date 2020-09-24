package sk.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;
import sk.pages.AbstractPageComponent;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static sk.constants.MenuItems.*;

public class MainMenu extends AbstractPageComponent {
    By menuItems = By.cssSelector(".navbar-nav a");

    public MainMenu(WebDriverDecorator driver) {
        super(driver);
    }

    public void checkItemIsPresent(String menuItem, boolean isPresent) {
        List<String> items = driver.getElementsText(menuItems);
        assertThat("Cannot find Main Menu items on page",items, notNullValue());
        //todo: New Post has icon amd @nbsp;
        items = items.stream().map(item -> item.trim()).collect(Collectors.toList());
        assertThat(String.format("Wrong expectation for presenting '%s' item in Main Menu", menuItem),
                items.contains(menuItem), equalTo(isPresent));
    }

    //find all items and select by index in List<Element>
    public void selectItemByName(String menuItem) {
        By item = By.xpath(
                String.format(".//ul[contains(@class, 'navbar-nav')]//a[contains(text(), '%s')]", menuItem)
        );
        driver.clickOn(item);
    }

    //todo: can refactor to one single function
    @Step("Open 'Sign In' page")
    public void openSightIn() {
        this.selectItemByName(SIGN_IN.getDisplayText());
        driver.waitForAngular();
    }

    @Step("Open 'Sign Up' page")
    public void openSightUp() {
        this.selectItemByName(SIGN_UP.getDisplayText());
        driver.waitForAngular();
    }

    @Step("Open 'Settings' page")
    public void openSettings() {
        this.selectItemByName(SETTINGS.getDisplayText());
        driver.waitForAngular();
    }

    @Step("Open 'New Post' page")
    public void openNewPost() {
        this.selectItemByName(NEW_POST.getDisplayText());
        driver.waitForAngular();
    }
}
