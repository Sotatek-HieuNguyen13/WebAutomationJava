package automation;

import model.Product;
import utils.ProductComparatorByPrice;
import org.testng.annotations.*;
import pages.AmazonPage;
import pages.EbayPage;
import utils.CommonService;
import utils.Containts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest extends BaseTest {
    private AmazonPage amazonPage;
    private EbayPage ebayPage;
    private CommonService commonService;
    private Containts containts;
    String text = "iPhone 11";

    List<Product> products = new ArrayList<Product>();

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        init();
        commonService = new CommonService(driver);
    }

    @Test
    public void searchTest() {
        goToUrl(Containts.url_amazon);
        amazonPage = new AmazonPage(driver);
        amazonPage.enterSearchValue(text);
        amazonPage.isDisplayResult();
        products.addAll(amazonPage.getAllProducts());

        goToUrl(Containts.url_ebay);
        ebayPage = new EbayPage(driver);
        ebayPage.enterSearchValue(text);
        ebayPage.isDisplayResult();
        ebayPage.getAllProducts();
        products.addAll(ebayPage.getAllProducts());

        //Sort
        Collections.sort(products, new ProductComparatorByPrice());

        //Print product list
        for (Product product : products) {
            product.inforProduct();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException{
        closeBrowser();
    }

}
