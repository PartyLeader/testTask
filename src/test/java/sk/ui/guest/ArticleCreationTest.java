package sk.ui.guest;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sk.constants.FeatureType;
import sk.ui.BaseTest;

import static sk.constants.FeedItems.GLOBAL_FEED;
import static sk.constants.FeedItems.YOUR_FEED;
import static sk.constants.MenuItems.*;

public class ArticleCreationTest extends BaseTest {

    @BeforeMethod
    public void openHomePage() {
        mainPageSteps.openHomePage();
    }

    @Test(enabled = false, description = "Skipped test")
    public void simpleTest() {
        Assert.assertEquals(1,1, "Positive scenario");
    }

    @Test(description = FeatureType.TC_5)
    public void globalFeedPagination() {
        mainPageSteps.selectGlobalFeed();
        mainPageSteps.checkArticleNumbersOnPage(10);
    }

    @Test(description = FeatureType.TC_1)
    public void verifyThatGuestCannotCreateArticle() {
        mainPageSteps.checkItemIsPresentInMenu(NEW_POST, false);
        mainPageSteps.checkItemIsPresentInMenu(HOME, true);
        mainPageSteps.checkItemIsPresentInMenu(SIGN_IN, true);
        mainPageSteps.checkItemIsPresentInMenu(SIGN_UP, true);
        mainPageSteps.checkItemIsPresentInMenu(SETTINGS, false);
    }

    @Test(description = "Check Feed menu items for Guest")
    public void verifyFeedMenuItems() {
        mainPageSteps.checkItemIsPresentInFeedMenu(YOUR_FEED, false);
        mainPageSteps.checkItemIsPresentInFeedMenu(GLOBAL_FEED, true);
    }

    @Test(description = "Verify Page titles")
    public void verifyPageTitles() {
        mainPageSteps.getMainPage().getMainMenu().openSightIn();
        mainPageSteps.getSignInPage().verifyPageIsOpen();

        mainPageSteps.getMainPage().getMainMenu().openSightUp();
        mainPageSteps.getSignUpPage().verifyPageIsOpen();
    }
}
