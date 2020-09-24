package sk.steps;

import io.qameta.allure.Step;
import sk.config.WebDriverDecorator;
import sk.constants.FeedItems;
import sk.constants.MenuItems;
import sk.models.Article;
import sk.models.User;
import sk.pages.*;

import java.util.Objects;

import static sk.constants.MenuItems.HOME;
import static sk.constants.MenuItems.NEW_POST;
import static sk.constants.UserConstants.SERVICE_USER;

public class MainPageSteps {

    private WebDriverDecorator driver;
    private MainPage mainPage;
    private SignInPage signInPage;
    private SignUpPage signUpPage;
    private EditorPage editorPage;
    private SettingsPage settingsPage;

    //region Page declaration
    //we use Singleton pattern for parallel text execution.
    public MainPage getMainPage() {
        if(Objects.isNull(mainPage))
            mainPage = new MainPage(driver);
        return mainPage;
    }

    public SignInPage getSignInPage() {
        if(Objects.isNull(signInPage))
            signInPage = new SignInPage(driver);
        return signInPage;
    }

    public SignUpPage getSignUpPage() {
        if(Objects.isNull(signUpPage))
            signUpPage = new SignUpPage(driver);
        return signUpPage;
    }

    public EditorPage getEditorPage() {
        if(Objects.isNull(editorPage))
            editorPage = new EditorPage(driver);
        return editorPage;
    }

    public SettingsPage getSettingsPage() {
        if(Objects.isNull(settingsPage))
            settingsPage = new SettingsPage(driver);
        return settingsPage;
    }
    //endregion

    public MainPageSteps(WebDriverDecorator driver) {
        this.driver = driver;
    }

    @Step("I Open a Main page")
    public void open() {
        getMainPage().open(SERVICE_USER);
    }

    @Step("I Open a Home page")
    public void openHomePage() {
        getMainPage().getMainMenu().selectItemByName(HOME.getDisplayText());
    }

    //region Feed Menu
    @Step("Open Global Feed table")
    public void selectGlobalFeed() {
        getMainPage().getFeedMenu().openGlobalFeed();
        getMainPage().getArticleList().waitListLoading();
    }

    @Step("I Verify that feed menu item {feedItems.displayText} is present on page: {isPresent}")
    public void checkItemIsPresentInFeedMenu(FeedItems feedItems, boolean isPresent) {
        getMainPage().getFeedMenu().checkItemIsPresent(feedItems.getDisplayText(), isPresent);
    }

    //endregion

    //region Main Menu
    @Step("I Verify that menu item {menuItem.displayText} is present on page: {isPresent}")
    public void checkItemIsPresentInMenu(MenuItems menuItem, boolean isPresent) {
        getMainPage().getMainMenu().checkItemIsPresent(menuItem.getDisplayText(), isPresent);
    }

    @Step("I Verify that menu item {user.name} is present on page: {isPresent}")
    public void checkUserIsPresentInMenu(User user, boolean isPresent) {
        getMainPage().getMainMenu().checkItemIsPresent(user.getName().toLowerCase(), isPresent);
    }
    //endregion

    @Step("I Verify that article {article.articleTitle} is present on page: {isPresent}")
    public void verifyArticleIsPresentOnPage(Article article, boolean isPresent) {
        getMainPage().getArticleList().checkItemIsPresent(article, isPresent);
    }

    @Step("I Verify that page contains less that {numbers} articles")
    public void checkArticleNumbersOnPage(int numbers) {
        getMainPage().getArticleList().checkItemsOnPageLessThan(numbers);
    }

    @Step("Create article")
    public void createArticle(Article article) {
        getMainPage().getMainMenu().openNewPost();
        getEditorPage()
                .fillArticle(article.getArticleTitle(), article.getStatus(), article.getBody(), article.getTagsAsString())
                .clickPublishButton();
    }

    @Step("Authorize as {user.name}")
    public void authorize(User user) {
        getMainPage().getMainMenu().openSightIn();
        getSignInPage().verifyPageIsOpen()
                .fillUser(user.getName(), user.getPassword())
                .clickSignInButton();
    }

    @Step("Create a new User as {user.name}")
    public void createUser(User user) {
        getMainPage().getMainMenu().openSightUp();
        getSignUpPage()
                .fillUser(user.getName(), user.getPassword(), user.getEmail())
                .clickSignUpButton();
    }
}
