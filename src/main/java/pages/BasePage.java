package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    Actions action;

    private final int timeoutWait = 10;
    private final int timeOutInSeconds = 5;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, timeOutInSeconds);
        action = new Actions(driver);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, timeoutWait);
            wait.until(expectation);
        } catch (Exception e) {
            Assert.fail("Timeout waiting for Page Load request");
        }
    }

    public void setTextValue(WebElement element, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(value);
    }

    public void sendKeysEnter(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    public void scrollToElement(WebElement element) {
        action.moveToElement(element).build().perform();
    }

}