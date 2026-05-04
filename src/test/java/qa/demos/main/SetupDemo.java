package qa.demos.main;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.demos.CheckoutDemo;
import qa.driver.BaseWindowTest;
import qa.demos.AddToCartDemo;
import qa.demos.LoginDemo;
import qa.driver.BaseWindowlessTest;
import qa.utils.Users;

public class SetupDemo extends BaseWindowlessTest {

    @DataProvider(name = "users", parallel = true)
    public Object[][] users() {
        return new Object[][] {
                {Users.STANDARD},
                {Users.LOCKED},
                {Users.PROBLEM},
                {Users.GLITCH},
                {Users.ERROR},
                {Users.VISUAL}
        };
    }


    @Test(dataProvider = "users")
    private void init(Users user) throws InterruptedException {
        WebDriver driver = getDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        LoginDemo.login(driver, user);
    }
}
