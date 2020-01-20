package sk.pages.components;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;

public class PopoverDropdown {
    WebDriverDecorator driver;

    public PopoverDropdown(WebDriverDecorator driver) {
        this.driver = driver;
    }

    private By getOptionByText(String optionText) {
        return By.xpath(String.format(".//div[contains(@class, 'a-dropdown')]//li[.=%s]", optionText));
    }


    public void selectItem(String itemName) {
        getOptionByText(itemName);
    }

}
