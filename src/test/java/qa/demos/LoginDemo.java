package qa.demos;

import org.openqa.selenium.*;
import qa.error.ErrorHandler;
import qa.utils.Users;

public class LoginDemo {
    public static void login(WebDriver driver, Users user){
        driver.findElement(By.id("user-name")).sendKeys(user.getUsername());
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
        if(!ErrorHandler.isError(driver)) {
            System.out.println("Login has been successful");
            AddToCartDemo.addCart(driver);
        }
        else {
            System.out.println("Login Failed");
        }
    }
}
