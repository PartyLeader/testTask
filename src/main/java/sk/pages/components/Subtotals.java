package sk.pages.components;

import org.openqa.selenium.By;
import org.testng.Assert;
import sk.config.WebDriverDecorator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static sk.helpers.FormatHelper.toAmount;

public class Subtotals {

    private WebDriverDecorator driver;

    private By container;

    private By label = By.cssSelector("#sc-subtotal-label-buybox");
    private By amount = By.cssSelector("#sc-subtotal-amount-buybox");
    private final String labelRegex = "Subtotal (/d+ items):";

    public Subtotals(WebDriverDecorator driver, By container) {
        this.driver = driver;
        this.container = container;
    }

    public void checkCount(Integer countInStock) {
        String labelText = driver.getElementText(driver.chained(container, label));
        Pattern p = Pattern.compile(labelRegex);
        Matcher m = p.matcher(labelText);
        while(m.find()){
            Assert.assertEquals(m.group(), countInStock.toString(), "Wrong items in Subtotal");
        }
    }

    public void checkAmount(Double expectedAmount) {
        String labelText = driver.getElementText(driver.chained(container, amount));
        Assert.assertEquals(labelText, toAmount(expectedAmount), "Wrong items in Subtotal");
    }
}
