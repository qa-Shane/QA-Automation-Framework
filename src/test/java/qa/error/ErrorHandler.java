package qa.error;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ErrorHandler {

    public static boolean isError(WebDriver driver){
        WebElement errorMessage = null;
        try {
            errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            return true;
        }catch (NoSuchElementException _){}
        Assert.assertNull(errorMessage, "Action could not be performed with the data entered!");
        return false;
    }
}
