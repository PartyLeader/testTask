package sk.steps;

import io.qameta.allure.Step;
import sk.config.WebDriverDecorator;
import sk.pages.MainPage;
import sk.pages.components.Sector;

import java.util.List;
import java.util.Objects;

public class MainPageSteps {

    private WebDriverDecorator driver;
    private MainPage mainPage;

    public MainPage getMainPage() {
        if(Objects.isNull(mainPage))
            mainPage = new MainPage(driver);
        return mainPage;
    }

    public MainPageSteps(WebDriverDecorator driver) {
        this.driver = driver;
    }

    @Step("I Open a Main page")
    public void open() {
        getMainPage().open();
    }

    @Step("I Start working with sector {sectorName}")
    public Sector getSector(String sectorName) {
        return new Sector(driver, sectorName);
    }

    @Step("I Start working with sector by index {index}")
    public Sector getSectorByIndex(int index) {
        return new Sector(driver, index);
    }

    @Step("I Verify that sectors are present")
    public void checkSectorNames(List<String> expectedNames) {
        getMainPage().checkSectorNames(expectedNames);
    }
}
