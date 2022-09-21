package pages;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class EbayPage extends BasePage {
    public EbayPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "_nkw")
    WebElement inputSearch;

    @FindBy(xpath = "//h1[contains(text(),'+ results for ')]")
    WebElement resultSearch;

    public void enterSearchValue(String value) {
        setTextValue(inputSearch, value);
        sendKeysEnter(inputSearch);
    }

    public void isDisplayResult() {
        Assert.assertTrue(resultSearch.isDisplayed());
    }

    public List<Product> getAllProducts() {
        List<WebElement> elementList = getDriver().findElements(By.xpath("//div[@id='srp-river-results']/ul/li[@class='s-item s-item__pl-on-bottom s-item--watch-at-corner']"));
        waitForPageLoad();
        List<Product> listProduct = new ArrayList<>();
        double price = 0.0;

        for (WebElement e : elementList) {
            scrollToElement(e);
            Product product = new Product();
            product.setWebName("Ebay.com");
            product.setProuductName(e.findElement(By.className("s-item__title")).getText());

            String element = e.findElement(By.className("s-item__price")).getText().replaceAll(",", "").replaceAll("VND", "");
            price = Double.parseDouble(element) / 23673.00; //covert to USD
            product.setProductPrice(price);

            product.setProductLink(e.findElement(By.className("s-item__link")).getAttribute("href"));
            listProduct.add(product);
        }

        return listProduct;
    }
}
