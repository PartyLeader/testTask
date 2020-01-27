package sk.steps;

import io.qameta.allure.Step;
import sk.config.WebDriverDecorator;
import sk.pages.CartPage;

import java.util.List;
import java.util.Objects;

public class CartPageSteps {
    private WebDriverDecorator driver;
    private CartPage cartPage;

    public CartPage getCartPage() {
        if(Objects.isNull(cartPage))
            cartPage = new CartPage(driver);
        return cartPage;
    }

    public CartPageSteps(WebDriverDecorator driver) {
        this.driver = driver;
    }

    @Step("I Open Cart page")
    public void open() {
        getCartPage().open();
    }

    @Step("I Check count items in Cart and verify calculated price")
    public void checkSubtotals(Integer countInStock, Double price) {
        getCartPage().checkSubtotals(countInStock, price);
    }

    @Step("I Check Items names in Cart")
    public void checkItemsInCart(List<String> itemsList) {
        getCartPage().checkItemsInCart(itemsList);
    }
}
