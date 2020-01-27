package sk.pages;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;
import sk.pages.components.PopoverDropdown;

import static sk.helpers.FormatHelper.fromAmount;

public class DetailsPage extends AbstractPage {
    private PopoverDropdown stockDropdown;
    private By stock = By.cssSelector("#selectQuantity");
    private By addToCardButton = By.cssSelector("#add-to-cart-button");
    private By price = By.cssSelector("#price_inside_buybox");
    private By title = By.cssSelector("#productTitle");
    private By attachCloseButton = By.cssSelector("#attach-desktop-sideSheet #attach-close_sideSheet-link");

    public DetailsPage(WebDriverDecorator webDriver) {
        this.driver = webDriver;
    }

    public void addInStock(int count) {
        stockDropdown = new PopoverDropdown(this.driver, stock);
        stockDropdown.open();
        stockDropdown.selectItem(count);
    }

    public void addToCard() {
        driver.clickOn(addToCardButton);
    }

    public void closeInformationWindowIfNeeded() {
        if(driver.isVisible(attachCloseButton)) {
            driver.clickOn(attachCloseButton);
        }
    }

    public Double getItemPrice() {
        return fromAmount(driver.getElementText(price));
    }

    public String getItemName() {
        return driver.getElementText(title);
    }
}
