package sk.pages.components;

import org.openqa.selenium.By;
import sk.config.WebDriverDecorator;
import sk.models.Article;
import sk.pages.AbstractPageComponent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class ArticleList extends AbstractPageComponent {
    String articleItemXpath = ".//app-article-list-item";
    String bodyXpath = "//a[contains(@class, 'preview-link')]";
    String articleTitleXpath = "//h1[text() = '%s']";
    String loadingTextXpath = ".//div[contains(text(), 'Loading articles...')]";

    public ArticleList(WebDriverDecorator driver) {
        super(driver);
    }

    private By getArticle(Article article) {
        return By.xpath(String.format(articleItemXpath + bodyXpath + articleTitleXpath, article.getArticleTitle()));
    }

    public void checkItemIsPresent(Article article, boolean isPresent) {
        assertThat(String.format("Wrong expectation for presenting '%s' article on page", article.getArticleTitle()),
                driver.isVisible(getArticle(article)), equalTo(isPresent));
    }

    public void checkItemsOnPageLessThan(int number) {
        assertThat("Number of articles on page must be less of equals " + number,
                driver.getElementsText(By.xpath(articleItemXpath + bodyXpath)).size(), lessThanOrEqualTo(number));
    }

    public void waitListLoading() {
        //wait while loadingText isn't has hidden attribute
        driver.waitForElementDisappear(By.xpath(loadingTextXpath + "[not(@hidden)]"));
    }
}
