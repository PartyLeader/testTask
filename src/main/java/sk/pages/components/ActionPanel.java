package sk.pages.components;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;

public class ActionPanel {
    WebDriverDecorator driver;

    By container = By.id("superleafActionPanel");
    By priceInsideBuybox = By.id("price_inside_buybox");

    public ActionPanel(WebDriverDecorator driver) {
        this.driver = driver;
    }



}

