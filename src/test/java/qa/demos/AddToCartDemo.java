package qa.demos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.products.Products;
import qa.products.SauceProduct;

import java.util.Map;

public class AddToCartDemo {
    public static void addCart(WebDriver driver){
        Map<SauceProduct, WebElement> productMap = Products.getProducts(driver);
        for(SauceProduct products : productMap.keySet()){
            if(products.price() < 15){
                WebElement element = productMap.get(products);
                element.findElement(By.xpath(".//button")).click();
            }
        }
        try {
            CheckoutDemo.checkout(driver);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
