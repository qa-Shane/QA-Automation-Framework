package qa.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Products {
    public static Map<SauceProduct, WebElement> getProducts(WebDriver driver){
        Map<SauceProduct, WebElement> elementMap = new HashMap<>();
        List<WebElement> rawProductList = driver.findElements(By.xpath("//div[@data-test='inventory-item']"));
        for(WebElement product : rawProductList){
            String title = product.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
            String desc = product.findElement(By.xpath(".//div[@data-test='inventory-item-desc']")).getText();
            double price = 0;
            try {
                price = Double.parseDouble(product.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText().replace('$', ' ').strip());
            }catch (NumberFormatException _){
                System.out.println("Could not convert the price");
            }
            SauceProduct sauceProduct = new SauceProduct(title,price,desc);
            elementMap.put(sauceProduct, product);
        }

        return elementMap;
    }
}
