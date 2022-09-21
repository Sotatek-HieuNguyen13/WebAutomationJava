package pages;

import model.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

public class AmazonPage extends BasePage {

    public AmazonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "twotabsearchtextbox")
    WebElement inputSearch;

    @FindBy(xpath = "//span[contains(text(),'results for')]")
    WebElement resultSearch;

    public void enterSearchValue(String value) {
        setTextValue(inputSearch, value);
        sendKeysEnter(inputSearch);
    }

    public void isDisplayResult() {
        Assert.assertTrue(resultSearch.isDisplayed());
    }

    public List<Product> getAllProducts() {
        List<WebElement> elementList = getDriver().findElements(By.xpath("//div[@data-component-type='s-search-result']//div[@class='a-section']//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        waitForPageLoad();
        List<Product> listProduct = new ArrayList<>();

        for (WebElement e : elementList) {
            scrollToElement(e);
            Product product = new Product();
            product.setWebName("Amazon.com");
            product.setProuductName(e.findElement(By.cssSelector("[class='a-size-medium a-color-base a-text-normal']")).getText());
            try {
                if (e.findElement(By.cssSelector("[class='a-price-whole']")).getText() != null) {
                    product.setProductPrice(Double.parseDouble(e.findElement(By.cssSelector("[class='a-price-whole']")).getText()));
                } else {
                    product.setProductPrice(0.0);
                }
            } catch (NotFoundException ex) {
                product.setProductPrice(0.0);
            }

            product.setProductLink(e.findElement(By.cssSelector("[class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']")).getAttribute("href"));
            listProduct.add(product);
        }

        return listProduct;
    }
}
