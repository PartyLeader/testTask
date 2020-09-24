package sk.ui.writer;

import org.testng.annotations.Test;
import sk.constants.FeatureType;
import sk.ui.WriterTest;

import static sk.constants.ArticleConstants.NEW_ARTICLE;
import static sk.constants.FeedItems.GLOBAL_FEED;
import static sk.constants.FeedItems.YOUR_FEED;
import static sk.constants.MenuItems.*;

public class ArticleCreationTest extends WriterTest {

    @Test(description = FeatureType.TC_2)
    public void verifyMainMenuItems() {
        mainPageSteps.checkItemIsPresentInMenu(HOME, true);
        mainPageSteps.checkItemIsPresentInMenu(NEW_POST, true);
        mainPageSteps.checkItemIsPresentInMenu(SIGN_IN, false);
        mainPageSteps.checkItemIsPresentInMenu(SIGN_UP, false);
        mainPageSteps.checkItemIsPresentInMenu(SETTINGS, true);
        mainPageSteps.checkUserIsPresentInMenu(WRITER, true);
    }

    @Test(description = FeatureType.TC_3)
    public void verifyThatWriterCanCreateArticle() {
        mainPageSteps.createArticle(NEW_ARTICLE());
        mainPageSteps.openHomePage();
        mainPageSteps.selectGlobalFeed();
        mainPageSteps.verifyArticleIsPresentOnPage(NEW_ARTICLE(), true);
    }

    @Test(description = FeatureType.TC_4)
    public void globalFeedPagination() {
        mainPageSteps.selectGlobalFeed();
        mainPageSteps.checkArticleNumbersOnPage(10);
    }

    @Test(description = "Check Feed menu items for Writer")
    public void verifyFeedMenuItems() {
        mainPageSteps.openHomePage();
        mainPageSteps.checkItemIsPresentInFeedMenu(YOUR_FEED, true);
        mainPageSteps.checkItemIsPresentInFeedMenu(GLOBAL_FEED, true);
    }

    @Test(description = "Verify Page titles")
    public void verifyPageTitles() {
        mainPageSteps.getMainPage().getMainMenu().openNewPost();
        mainPageSteps.getEditorPage().verifyPageIsOpen();

        mainPageSteps.getMainPage().getMainMenu().openSettings();
        mainPageSteps.getSettingsPage().verifyPageIsOpen();

        //todo add steps for User settings and Home page
    }
}
