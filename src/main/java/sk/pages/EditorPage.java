package sk.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EditorPage extends AbstractPage {
    private static final String pageAddress = "#/editor";

    By title = By.cssSelector("h1");
    By titleField = By.xpath("(.//input[@type='text'])[1]");
    By aboutField = By.xpath("(.//input[@type='text'])[2]");
    By bodyField = By.cssSelector("textarea");
    By tagsField = By.xpath("(.//input[@type='text'])[3]");
    By publishButton = By.cssSelector("button[type='button']");

    public EditorPage(WebDriverDecorator webDriver) {
        super(webDriver);
    }

    @Step("Verify that page is Open")
    public EditorPage verifyPageIsOpen() {
        System.out.println(driver.getElementsText(title));
        assertThat("Wrong title expectation", driver.isPresentNow(title), equalTo(false));
        return this;
    }


    public void clickPublishButton() {
        driver.clickOn(publishButton);
    }

    @Step("Fill all mandatory fields for article")
    public EditorPage fillArticle(String title, String about, String body, String tags) {
        driver.cleanAndType(titleField, title);
        driver.cleanAndType(aboutField, about);
        driver.cleanAndType(bodyField, body);
        driver.cleanAndType(tagsField, tags);
        return this;
    }
}
