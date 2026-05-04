package qa.demos;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import qa.error.ErrorHandler;
import qa.utils.OrderDetails;
import qa.utils.Person;

public class CheckoutDemo {
    public static void checkout(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
        driver.findElement(By.xpath("//button[@data-test='checkout']")).click();

        Person customer = new Person("John", "Marley", "92443");

        driver.findElement(By.xpath("//input[@data-test='firstName']")).sendKeys(customer.firstName());
        driver.findElement(By.xpath("//input[@data-test='lastName']")).sendKeys(customer.lastName());
        driver.findElement(By.xpath("//input[@data-test='postalCode']")).sendKeys(customer.postalCode());

        driver.findElement(By.xpath("//input[@data-test='continue']")).click();

        if(!ErrorHandler.isError(driver))
            System.out.println("Checkout has been successful");
        OrderDetails details = new OrderDetails(driver);
        details.getDetails();
        try {
            driver.findElement(By.xpath("//button[@data-test='finish']")).click();
        }catch (NoSuchElementException e){
            System.out.println("Unable to complete checkout!");
        }
    }
}
