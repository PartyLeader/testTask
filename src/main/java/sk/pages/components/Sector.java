package sk.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import sk.config.WebDriverDecorator;

import java.util.List;

public class Sector {

    WebDriverDecorator driver;

    By container;

    By cardItems = By.cssSelector(".feed-carousel-card");


    public Sector(WebDriverDecorator driver, String sectorName) {
        this.driver = driver;
        container = By.xpath(
                String.format(".//div[contains(@class, 'product-shoveler')][.//h2/span[text()='%s']]", sectorName)
        );
    }

    public Sector(WebDriverDecorator driver, int sectorIndex) {
        if(sectorIndex <= 0) Assert.fail("Wrong index value for Sector class");
        //index from 1
        this.driver = driver;
        container = By.xpath(
                String.format("(.//div[contains(@class, 'product-shoveler')])[%d]", sectorIndex)
        );
    }

    public void selectCardByIndex(int index) {
        List<WebElement> cards = driver.findElement(container).findElements(cardItems);
        Assert.assertNotNull(cards, "Cards aren't exist");
        cards.get(index).click();
    }

}
