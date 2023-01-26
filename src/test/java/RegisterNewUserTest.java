import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterNewUserTest extends TestBase{

    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMessageBlock = By.id("error-message");
    Faker faker = new Faker();

    @Test
    public void registerNewUser() throws InterruptedException {
        // throws InterruptedException - нужно тогда, если мы будем дальше в коде использовать паузы Thread.sleep(ms)

        // Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        // Act
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        // Assert
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err); // тут надо прописать, как будет выглядеть сообщение при ошибке (или с помощью переменной)

    }

}
