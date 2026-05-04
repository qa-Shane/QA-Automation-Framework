package qa.demos.main;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import qa.demos.CheckoutDemo;
import qa.driver.BaseWindowTest;
import qa.demos.AddToCartDemo;
import qa.demos.LoginDemo;

public class SetupDemo extends BaseWindowTest {
    @Test
    private void init() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        LoginDemo.login(driver);
        Thread.sleep(1000);
        AddToCartDemo.addCart(driver);
        CheckoutDemo.checkout(driver);
    }
}
