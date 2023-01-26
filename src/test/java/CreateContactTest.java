import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTest extends Login{
    @Test
    public void CreateNewContact(){
        String firstName = "";
        String lastName = "";
        String description = "";
        Number expectedCountRow = 1;

        // Click on the button "Add new contact"
        driver.findElement(By.cssSelector("[href=\"/contacts\"]")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role=\"dialog\"]")));
        // Fill field First name
        fillField(firstName, By.xpath("//*[@role=\"dialog\"]//*[@placeholder=\"First name\"]"));
        // Fill field Last name
        fillField(lastName, By.xpath("//*[@role=\"dialog\"]//*[@placeholder=\"Last name\"]"));
        // Fill field About
        fillField(description, By.xpath("//*[@role=\"dialog\"]//*[@placeholder=\"About\"]"));
        // Click on the button "Save"
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role=\"dialog\"]")));
        // Filter by creation name
        fillField(firstName, By.xpath("//*[@placeholder=\"Search...\"]"));

        Number actualCountRow = driver.findElements(By.className("List-group")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
        // Expected result: Created contact show with correct data in the contact table
        
    }
}


// TODO разобраться как пользоваться java-uuid-generator