package sk.steps;

import io.qameta.allure.Step;
import sk.config.WebDriverDecorator;
import sk.pages.DetailsPage;

import java.util.Objects;

public class DetailPageSteps {
    private WebDriverDecorator driver;
    private DetailsPage detailsPage;

    public DetailsPage getDetailsPage() {
        if(Objects.isNull(detailsPage))
            detailsPage = new DetailsPage(driver);
        return detailsPage;
    }

    public DetailPageSteps(WebDriverDecorator driver) {
        this.driver = driver;
    }

    @Step("I Add items to Stock")
    public void addInStock(int count) {
        getDetailsPage().addInStock(count);
    }

    @Step("I Click [Add to Card] button")
    public void addToCard() {
        getDetailsPage().addToCard();
    }

    @Step("I Check popup window and close if presented")
    public void closeInformationWindowIfNeeded() {
        getDetailsPage().closeInformationWindowIfNeeded();
    }

    @Step("I Get price for selected Item")
    public Double getItemPrice() {
        return getDetailsPage().getItemPrice();
    }

    @Step("I Get name for selected Item")
    public String getItemName() {
        return getDetailsPage().getItemName();
    }
}
