package api.tests;

import com.github.dockerjava.transport.DockerHttpClient;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiBase {
    Faker faker = new Faker();

    final String BASE_URI = "http://phonebook.telran-edu.de:8080";
    final String API_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MjEwNjk3ODI5Nn0.GM1wsoRV2QoAsD6wKmIk7N49DDpuCejK4BC9H9YItJvesH5vft8HO2uqTPnGQJwJ5oXKS2OILqP1yoanMnIMkA";

    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("Access-Token", API_KEY)
            .build();

    public Response doPostRequest(String endPoint, Integer responseCode, Object dto){
        Response resp = RestAssured.given()
                .spec(spec)
                .body(dto)
                .when()
                .log().all()
                .post(endPoint)
                .then().log().all()
                .extract().response();
        resp.then().assertThat().statusCode(responseCode);
        return resp;
    }
}
