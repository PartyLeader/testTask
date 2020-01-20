package sk.pages;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;
import sk.pages.components.PopoverDropdown;

public class DetailsPage extends AbstractPage {
    private PopoverDropdown stockDropdown;
    private By stock = By.cssSelector"#selectQuantity .a-icon-dropdown"

    public DetailsPage(WebDriverDecorator webDriver) {
        this.driver = webDriver;
    }

    public void addInStock(int count) {
        stockDropdown = new PopoverDropdown(this.driver);
        stockDropdown.selectItem(count);
    }
}
