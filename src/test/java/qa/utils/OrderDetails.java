package qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderDetails {
    private final WebDriver driver;
    public OrderDetails(WebDriver driver){
        this.driver = driver;
    }

    private WebDriver getDriver(){return driver;}

    public void getDetails(){
        List<WebElement> cartList = getDriver().findElements(By.xpath("//div[@data-test='inventory-item']"));
        System.out.println("ORDER DETAILS\n-------------------\n");
        for(WebElement cartItem : cartList){
            String quantity = cartItem.findElement(By.xpath(".//div[@data-test='item-quantity']")).getText();
            String itemName = cartItem.findElement(By.xpath(".//div[@data-test='inventory-item-name']")).getText();
            String price = cartItem.findElement(By.xpath(".//div[@data-test='inventory-item-price']")).getText();


            System.out.println("Bought " + quantity + " " + itemName + " for " + price);
        }
        try {
            String cardInfo = getDriver().findElement(By.xpath("//div[@data-test='payment-info-value']")).getText();
            String subtotal = getDriver().findElement(By.xpath("//div[@data-test='subtotal-label']")).getText().split(": ")[1];
            String tax = getDriver().findElement(By.xpath("//div[@data-test='tax-label']")).getText();
            String total = getDriver().findElement(By.xpath("//div[@data-test='total-label']")).getText();
            System.out.println("Paid with card: " + cardInfo + "\nSubtotal: "+subtotal+"\n"+tax+"\n"+total);
        }catch (NoSuchElementException e){
            System.out.println("Could not complete order!");
        }

    }
}

