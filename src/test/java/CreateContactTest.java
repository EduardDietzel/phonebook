import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest extends ChangeLanguage{

    Faker faker = new Faker();

    private void openAddNewContact() {
        driver.findElement(By.cssSelector("[href=\"/contacts\"]")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role=\"dialog\"]")));
    }
    @Test
    public void CreateNewContact() throws InterruptedException {
        // String firstName = "";
        String firstName = faker.internet().uuid();
        // String lastName = "";
        String lastName = faker.internet().uuid();
        // String description = "";
        String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        // Click on the button "Add new contact"
        openAddNewContact();

        // Fill field First name
        fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder=\"First name\"]"));
        // Fill field Last name
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder=\"Last name\"]"));
        // Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder=\"About\"]"));

        // Click on the button "Save"

        // driver.findElement(By.cssSelector("//form//button[@type='submit']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
        checkItemText(By.id("contact-first-name"),firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"),lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"),description, "Actual description is not equal expected description");
        // driver.findElement(By.cssSelector("[href='/']"));
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();

        // Filter by creation name
        fillField(firstName, By.xpath("//*[@placeholder=\"Search...\"]"));

//        Number actualCountRow = driver.findElements(By.className("list-group")).size();

        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
        // Expected result: Created contact show with correct data in the contact table
        
    }


}


// TODO разобраться как пользоваться java-uuid-generator