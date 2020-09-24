package sk.pages.components;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;
import sk.pages.AbstractPageComponent;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static sk.constants.FeedItems.GLOBAL_FEED;
import static sk.constants.FeedItems.YOUR_FEED;

public class FeedMenu extends AbstractPageComponent {
    By menuItems = By.cssSelector(".feed-toggle a");

    public FeedMenu(WebDriverDecorator driver) {
        super(driver);
    }

    public void checkItemIsPresent(String menuItem, boolean isPresent) {
        List<String> items = driver.getElementsText(menuItems);
        assertThat("Cannot find Feed Menu items on page",items, notNullValue());
        //todo: New Post has icon amd @nbsp;
        items = items.stream().map(item -> item.trim()).collect(Collectors.toList());
        assertThat(String.format("Wrong expectation for presenting '%s' item in Feed Menu", menuItem),
                items.contains(menuItem), equalTo(isPresent));
    }

    //find all items and select by index in List<Element>
    public void selectItemByName(String menuItem) {
        By item = By.xpath(
                String.format(".//div[contains(@class, 'feed-toggle')]//a[contains(text(), '%s')]", menuItem)
        );
        driver.clickOn(item);
    }

    public void openYourFeed() {
        this.selectItemByName(YOUR_FEED.getDisplayText());
    }

    public void openGlobalFeed() {
        this.selectItemByName(GLOBAL_FEED.getDisplayText());
    }
}
