package qa.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseWindowTest {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().browserVersion("147").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/brave-browser");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    public WebDriver getDriver(){return driver;}

    @AfterMethod
    public void teardown(){
        if(driver != null)
            driver.quit();
    }
}
