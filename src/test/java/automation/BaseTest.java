package automation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void goToUrl(String baseUrl) {
        try {
            driver.get(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeBrowser() {
        driver.quit();
    }
}
