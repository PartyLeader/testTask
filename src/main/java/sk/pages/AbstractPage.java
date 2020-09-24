package sk.pages;

import io.qameta.allure.Step;
import sk.config.WebDriverDecorator;
import sk.models.User;

import static sk.constants.ApplicationConstants.APP_URL;
import static sk.helpers.URLHelper.addUserPassToURL;

public abstract class AbstractPage implements Page {

    protected WebDriverDecorator driver;

    AbstractPage(WebDriverDecorator driver) {
        this.driver = driver;
    }

    @Step("Open page: {pageURL}")
    public void open(String pageURL) {
        driver.navigate().to(APP_URL + pageURL);
    }

    @Step("Open page: {pageURL}")
    public void openForUser(String pageURL, User user) {
        String newURL = addUserPassToURL(user.getName(), user.getPassword(), APP_URL + pageURL);
        driver.navigate().to(newURL);
    }

    @Override
    public void open() {
        driver.navigate().to(APP_URL);
    }
}
