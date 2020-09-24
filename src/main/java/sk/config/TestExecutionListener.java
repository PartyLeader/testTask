package sk.config;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestExecutionListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object test = result.getInstance();
        if(test instanceof AbstractTest) {
            WebDriverDecorator driver = ((AbstractTest) test).getDriver();
            driver.takeScreenshot();
        }
    }
}
