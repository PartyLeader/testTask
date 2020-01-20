package sk.config;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class WebDriverDecorator implements WebDriver{

    private final Logger log = Logger.getLogger(WebDriverDecorator.class);

    private WebDriverWait wait;
    private WebDriver driver;

    private long implicitWait;
    private long explicitWait;
    private boolean failOnException = false;

    public WebDriverDecorator() {
        System.setProperty("webdriver.chrome.driver", "src/main/drivers/chromedriver");
        EnvironmentConfig environmentConfig = new EnvironmentConfig("driver");
        this.implicitWait = environmentConfig.getLongProperty("implicit.wait");
        this.explicitWait = environmentConfig.getLongProperty("explicit.wait");

        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public WebElement findElement(By locator) {

        log.info("Waiting for presence of element: " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            log.error("Element: " + locator + " was not present in DOM after: " + explicitWait + " s");
            if (failOnException) {
                throw e;
            }
        }
        return driver.findElement(locator);
    }

    @Override
    public List<WebElement> findElements(By locator) {
        return this.findElements(locator, explicitWait);
    }

    public List<WebElement> findElements(By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        log.info("Waiting for presence of all elements: " + locator + " with timeout of: " + timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            log.error("Elements: " + locator + " were not present in DOM after: " + timeoutInSeconds + " s");
            if (failOnException) {
                throw e;
            }
        }
        return driver.findElements(locator);
    }

    public String getElementText(By locator) {
        log.info("Getting text of element:" + locator);
        return this.findElement(locator).getText();
    }

    public List<String> getElementsText(By locator) {
        log.info("Getting text of all elements: " + locator);
        return this.findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
