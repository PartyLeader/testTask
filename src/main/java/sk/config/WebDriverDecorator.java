package sk.config;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

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

    private WebElement findVisibleElement(By locator) {

        log.info("Waiting for visibility of element: " + locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.error("Element: " + locator + " was not visible after: " + explicitWait + " s");
            if (failOnException) {
                throw e;
            }
        }
        return driver.findElement(locator);
    }

    public void clickOn(By locator) {
        log.info("Clicking on element: " + locator);
        try {
            findVisibleElement(locator).click();
        } catch (WebDriverException e) {
            log.error("It was not possible to click on element " + locator);
            if (failOnException) {
                throw e;
            }
        }
    }

    public boolean isVisible(By locator) {
        log.info("Checking if " + locator + " is visible");
        return findVisibleElement(locator) != null;
    }

    public boolean isPresentNow(By locator) {
        log.info("Check if " + locator + " is presents now");
        List<WebElement> elements = this.findElements(locator);
        return elements.size() != 0;
    }

    public String getElementText(By locator) {
        log.info("Getting text of element:" + locator);
        return this.findElement(locator).getText();
    }

    public List<String> getElementsText(By locator) {
        log.info("Getting text of all elements: " + locator);
        return this.findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void waitForElementDisappear(By locator) {
        log.info("Wait for element to disappear: " + locator);
        this.wait.until(ExpectedConditions.invisibilityOfAllElements(this.findElements(locator)));
    }

    public void waitForElementDisappear(By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutInSeconds));
        log.info("Wait for element to disappear: " + locator);
        wait.until(ExpectedConditions.invisibilityOfAllElements(this.findElements(locator)));
    }

    public void cleanAndType(By locator, String text) {
        log.info("Cleaning: " + locator + "and type text: " + text);
        WebElement element = this.findVisibleElement(locator);
        element.clear();
        element.sendKeys(new CharSequence[] {String.valueOf(text)});
    }

    public void type(By locator, String text) {
        log.info("Type into: " + locator + " text: " + text);
        this.findVisibleElement(locator).sendKeys(new CharSequence[] {String.valueOf(text)});
    }

    public void executeJavaScript(String javaScript, By locator) {
        log.info("Execute JavaScript: " + javaScript + " on element: " + locator);
        ((JavascriptExecutor) driver).executeScript(javaScript, new Object[] {this.findElement(locator)});
    }

    public void executeJavaScript(String javaScript) {
        log.info("Execute JavaScript: " + javaScript + " on all page");
        ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public void waitForAngular() {
        this.waitForAngular("return getAllAngularTestabilities()[0].isStable()");
    }

    public void waitForAngular(String javaScript) {
        log.info("Wait for Angular: " + javaScript + " on all page");
        this.wait.until(driver -> {
            return ((JavascriptExecutor) driver).executeScript(javaScript, new Object[0]).equals(true);
        });
    }

    public By chained(By by1, By by2) {
        return new ByChained(by1, by2);
    }

    @Attachment(value = "PageObject screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        log.info("Taking screenshot on test failure");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
