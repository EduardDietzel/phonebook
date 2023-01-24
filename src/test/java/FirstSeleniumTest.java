import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm-password\"]");

    // before
    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        // driver.get("https://www.google.ru/");
        // driver.navigate().to("https://www.google.ru/");
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // test
    @Test
    public void loginWithValidData() {
        driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector(".btn.btn-info"));
    }

    @Test
    public void locators() {
        driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("/"));

        driver.findElement(By.id("irstname"));
        driver.findElement(By.cssSelector("#firstname input"));

        driver.findElement(By.tagName("p"));
        driver.findElement(By.cssSelector("p"));
        driver.findElement(By.xpath("//*[@id=\"registration-form\"]/div[1]/div/input"));
    }

    @Test
    public void registerNewUser() throws InterruptedException {
        String userData = "test@gmail.com";

        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
            Thread.sleep(1000);
        // fillField(userData, By.cssSelector("[placeholder=\"Email\"]"));
        fillField(userData, emailField);
            Thread.sleep(1000);
        fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        // driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys(userData);  // это мы удаляем после замены методом fillField
            Thread.sleep(1000);
        fillField(userData, By.cssSelector("[ng-reflect-name=\"confirm_password\"]"));
        // driver.findElement(By.name("confirm-password")).sendKeys(userData);
        // driver.findElement(By.name("//*[@name=\"confirm-password\"]")).sendKeys(userData);   // это по XPath
            Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();

    }

    private void fillField(String userData, By cssSelector) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(userData);
    }


    // after
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(10);
        if (driver != null) {
            // driver.quit();
        }
    }
}

