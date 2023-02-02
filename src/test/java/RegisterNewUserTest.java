import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
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
    By errorEmailMessageBlock = By.id("email-error-invalid");
    By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");
    Faker faker = new Faker();

    private void goToRegistrationPage() {
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));
    }

    private void fillRegistrationForm(String userData, String password) {
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
    }

    private void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }
    private void checkErrorMessage(By locator, String expectedErrorMessage) {
        String err = "Actual error message is not equal expected";
        checkItemText(locator, expectedErrorMessage, err);
    }


    // Positive
    @Test
    public void registerNewUserWithValidData() throws InterruptedException {
        // throws InterruptedException - нужно тогда, если мы будем дальше в коде использовать паузы Thread.sleep(ms)

        // Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        // Act
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        clickSignUpButton();
        // Assert
        checkErrorMessage(errorMessageBlock, expectedErrorMessage);
    }

    // Negative
    @Test
    public void registerNewUserWithInvalidData() throws InterruptedException {
        // throws InterruptedException - нужно тогда, если мы будем дальше в коде использовать паузы Thread.sleep(ms)

        // Arrange
        String userData = faker.internet().password();    // поменяли местами имейл и пароль
        String password = faker.internet().emailAddress();
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer then 20 characters.";

        // Act
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresent(errorMessageBlock));

        // Assert
        String err = "Actual error message is not equal expected";
        checkErrorMessage(errorEmailMessageBlock, expectedEmailErrorMessage);
        checkErrorMessage(errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);
    }

    // negative
    @Test
    public void registerExistingUser() throws InterruptedException {
        // throws InterruptedException - нужно тогда, если мы будем дальше в коде использовать паузы Thread.sleep(ms)

        // Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";
        // Act
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        clickSignUpButton();

        // Assert
//        String err = "Actual error message is not equal expected";
//        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err); // тут надо прописать, как будет выглядеть сообщение при ошибке (или с помощью переменной)
        checkErrorMessage(errorMessageBlock, expectedErrorMessage);
    }

}
