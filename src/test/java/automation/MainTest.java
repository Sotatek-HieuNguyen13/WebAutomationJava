package automation;

import model.Product;
import utils.ProductComparatorByPrice;
import org.testng.annotations.*;
import pages.AmazonPage;
import pages.EbayPage;
import utils.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest extends BaseTest {
    private AmazonPage amazonPage;
    private EbayPage ebayPage;
    String text = "iPhone 11";

    List<Product> products = new ArrayList<Product>();

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        init();
    }

    @Test
    public void searchTest() {
        goToUrl(Constant.url_amazon);
        amazonPage = new AmazonPage(driver);
        amazonPage.enterSearchValue(text);
        amazonPage.isDisplayResult();
        products.addAll(amazonPage.getAllProducts());

        goToUrl(Constant.url_ebay);
        ebayPage = new EbayPage(driver);
        ebayPage.enterSearchValue(text);
        ebayPage.isDisplayResult();
        ebayPage.getAllProducts();
        products.addAll(ebayPage.getAllProducts());

        //Sort price
        Collections.sort(products, new ProductComparatorByPrice());

        //Print product list
        for (Product product : products) {
            product.productInfor();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeBrowser();
    }

}
