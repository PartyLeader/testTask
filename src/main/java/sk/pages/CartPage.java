package sk.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import sk.config.WebDriverDecorator;
import sk.pages.components.Subtotals;

import java.util.List;


public class CartPage extends AbstractPage {

    private static final String pageAddress = "/gp/cart/view.html?ref_=nav_cart";

    private By subtotalsContainer = By.cssSelector("#gutterCartViewForm");
    private By itemTitle = By.className("sc-product-title");

    public void open() {
        open(pageAddress);
    }

    public CartPage(WebDriverDecorator webDriver) {
        this.driver = webDriver;
    }

    public void checkSubtotals(Integer countInStock, Double price) {
        Subtotals subtotals = new Subtotals(this.driver, subtotalsContainer);
        subtotals.checkCount(countInStock);
        subtotals.checkAmount(countInStock * price);
    }

    public void checkItemsInCart(List<String> itemsList) {
        List<String> itemsInChart = driver.getElementsText(itemTitle);
        Assert.assertEquals(itemsInChart, itemsList);
    }
}
