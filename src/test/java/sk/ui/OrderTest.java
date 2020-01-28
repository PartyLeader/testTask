package sk.ui;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.collections.Lists.newArrayList;

public class OrderTest extends BaseTest {

    @Test(enabled = false, description = "Skipped test")
    public void simpleTest() {
        Assert.assertEquals(1,1, "Positive scenario");
    }

    @Test(description = "Verify names of the all section on the main page")
    public void sectionVerification() {
        List<String> sectorNames = newArrayList("Discover Amazon2", "Best Sellers in Sports & Outdoors",
                "Best Sellers in Clothing, Shoes & Jewelry", "Amazon Top Sellers",
                "Best Sellers in Computers & Accessories", "Best Sellers in Cell Phones & Accessories",
                "Must have Wireless products", "Best Sellers in Beauty & Personal Care");
        mainPageSteps.checkSectorNames(sectorNames);
    }

    @Test(description = "Check Shopping Cart calculation")
    public void shoppingCartTest() {
        int stockCount = 8;
        List<String> titles = new ArrayList<>();
//        mainPage.getSector("Best Sellers in Sports & Outdoors")
        mainPageSteps.getSectorByIndex(3)
                    .selectCardByIndex(5);

        Double price = detailsPageSteps.getItemPrice();
        titles.add(detailsPageSteps.getItemName());
        detailsPageSteps.addInStock(stockCount);
        detailsPageSteps.addToCard();
        detailsPageSteps.closeInformationWindowIfNeeded();

        cartPageSteps.open();
        cartPageSteps.checkSubtotals(stockCount, price);
        cartPageSteps.checkItemsInCart(titles);
    }
}
