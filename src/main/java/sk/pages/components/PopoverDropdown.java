package sk.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;

public class PopoverDropdown {

    private WebDriverDecorator driver;
    private By container;

    private By selectSpan = By.cssSelector(".a-button-text");

    public PopoverDropdown(WebDriverDecorator driver, By sectorContainer) {
        this.driver = driver;
        this.container = sectorContainer;
    }

    private By getOptionByText(String optionText) {
        return By.xpath(String.format(".//div[contains(@class, 'a-dropdown')]//li[.=%s]", optionText));
    }

    @Step("Open dropdown")
    public void open() {
        driver.findElement(container).findElement(selectSpan).click();
    }

    //find element by index(path of xpath)
    @Step("Select row {count} in dropdown")
    public void selectItem(Integer count) {
        driver.clickOn(getOptionByText(count.toString()));
    }

}
