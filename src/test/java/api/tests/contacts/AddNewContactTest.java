package api.tests.contacts;

import api.model.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContactTest extends ApiBase{
    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;

    @Test
    public void createContactTest(){
        contactDto = new ContactDto();
        contactDto.setFirstName(faker.name().firstName());
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence(5));

        response = doPostRequest("/api/contact", 201, contactDto);

        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }


}
