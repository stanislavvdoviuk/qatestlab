package test;

import data.Currency;
import helper.NumberHelper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductComponent;
import pages.ProductsContainerComponent;

import java.util.ArrayList;
import java.util.List;

public class DiscountTest extends LocalTestRunner{
    @Test
    public void checkDiscount() throws InterruptedException {
        ProductsContainerComponent productsContainerComponent=new ProductsContainerComponent(getDriver());
        List<WebElement> productElements = productsContainerComponent.getProductElements();
        ProductComponent productComponent = new ProductComponent();

        for (WebElement element: productElements) {
            if (productComponent.containsProductDiscount(element)) {
                double price = productComponent.getDoublePrice(element);
                double regularPrice = productComponent.getDoubleRegularPrice(element);
                double discount  = productComponent.getDoubleDiscount(element);
                double expectedPrice = NumberHelper.round(regularPrice - (regularPrice / 100.0 * discount), 2);

                Assert.assertEquals(price, expectedPrice);
            }
        }

    }
}
