package sk.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import sk.config.WebDriverDecorator;
import sk.models.User;
import sk.pages.components.ArticleList;
import sk.pages.components.FeedMenu;
import sk.pages.components.MainMenu;

import java.util.List;

public class MainPage extends AbstractPage {
    private static final String pageAddress = "#/";

    private MainMenu mainMenu;
    private FeedMenu feedMenu;
    private ArticleList articleList;

    private By sectorNames = By.cssSelector("#main-content .as-title-block h2 span");

    public MainPage(WebDriverDecorator webDriver) {
        super(webDriver);
    }

    public void open() {
        open(pageAddress);
    }

    public void open(User user) {
        openForUser(pageAddress, user);
    }

    public List<String> getSectorNames() {
        return driver.getElementsText(sectorNames);
    }

    public void checkSectorNames(List<String> expectedNames) {
        Assert.assertEqualsNoOrder(expectedNames.toArray(), getSectorNames().toArray());
    }

    @Step("I Start working with mainMenu")
    public MainMenu getMainMenu() {
        if(mainMenu == null) {
            mainMenu = new MainMenu(driver);
        }
        return mainMenu;
    }

    @Step("I Start working with feedMenu")
    public FeedMenu getFeedMenu() {
        if(feedMenu == null) {
            feedMenu = new FeedMenu(driver);
        }
        return feedMenu;
    }

    @Step("I Start working with article list")
    public ArticleList getArticleList() {
        if(articleList == null) {
            articleList = new ArticleList(driver);
        }
        return articleList;
    }
}
