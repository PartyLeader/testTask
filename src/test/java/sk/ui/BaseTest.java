package sk.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sk.config.WebDriverDecorator;
import sk.pages.DetailsPage;
import sk.pages.MainPage;

import java.util.List;

import static org.testng.collections.Lists.newArrayList;


public class BaseTest {
    WebDriverDecorator driver = new WebDriverDecorator();
    MainPage mainPage;
    DetailsPage detailsPage;

    public WebDriverDecorator getDriver() {
        return this.driver;
    }

    @BeforeClass(alwaysRun = true)
    public void openPage() {
        this.mainPage = new MainPage(getDriver());
        mainPage.open();
    }

    @Test(enabled = false)
    public void simpleTest() {
       Assert.assertEquals(1,1, "Positive scenario");
    }

    @Test(description = "Verify names of the all section on the main page")
    public void sectionVerification() {
        List<String> sectorNames = newArrayList("Discover Amazon", "Best Sellers in Sports & Outdoors",
                "Best Sellers in Kitchen & Dining", "Best Sellers in Clothing, Shoes & Jewelry",
                "Amazon Top Sellers", "Trending in Video Games", "Best Sellers in Cell Phones & Accessories",
                "Best Sellers in Beauty & Personal Care");
        mainPage.checkSectorNames(sectorNames);
    }

    @Test
    public void simpleFalseTest() {
//        mainPage.getSector("Best Sellers in Sports & Outdoors")
        mainPage.getSectorByIndex(2)
                .selectCardByIndex(5);

        detailsPage = new DetailsPage(getDriver());
        detailsPage.addInStock(8);

    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        driver.getDriver().quit();
    }
}
