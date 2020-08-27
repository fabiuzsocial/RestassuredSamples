package io.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class RestassuredSomeTests {


    private String username = "username";
    private String password = "password";


    @Test
    public void testBookingEndpointExists() {

        baseURI = "https://restful-booker.herokuapp.com";

        when()
                .get(baseURI + "/booking")
                .then()
                .assertThat()
                .statusCode(200);
    }


    @Test
    public void testResponseLength() {

        baseURI = "https://restful-booker.herokuapp.com";
        String responseString = given()
                .headers("Content-Type", ContentType.JSON)
                .when()
                .get(baseURI + "/booking")
                .then().extract().response()
                .body().asString();
        assertEquals(162, responseString.length());

    }

    @Test
    public void testResponseContainsSomeJSON() {

        baseURI = "https://restful-booker.herokuapp.com/";

        Response response = given()
                .headers("Content-Type", ContentType.JSON)
                .when()
                .get("/booking")
                .then().extract()
                .response();
        List<Map<String, String>> jsonResponse = response.jsonPath().getList("$");

        assertEquals(1, jsonResponse.get(0).get("bookingid"));
    }

    @Test
    public void testGetUsersSpecificPartOfResponseBody() {

        Response response = given()
                .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/users")
                .then().contentType(ContentType.JSON).extract().response();

        String usernames = response.jsonPath().getString("username");
        System.out.println("get list of usernames " + usernames);

        List<Map<String, String>> jsonResponse = response.jsonPath().getList("$");
        System.out.println("get response " + jsonResponse);

        for (Map<String, String> item : jsonResponse) {
            System.out.println("item = " + item);
        }

        jsonResponse = response.jsonPath().getList("username");
        System.out.println("get first username from a list " + jsonResponse.get(0));

    }

    @Test
    public void testAddUser() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        String myjson = "{\n" +
                "  \"name\": \"Matt Madd\",\n" +
                "  \"username\": \"mattz\",\n" +
                "  \"email\": \"mat@mad.biz\",\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Kulas Light\",\n" +
                "    \"suite\": \"Apt. 556\",\n" +
                "    \"city\": \"Gwenborough\",\n" +
                "    \"zipcode\": \"92998-3874\",\n" +
                "    \"geo\": {\n" +
                "      \"lat\": \"-37.3159\",\n" +
                "      \"lng\": \"81.1496\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"phone\": \"1-770-736-8031 x56442\",\n" +
                "  \"website\": \"hildegard.org\",\n" +
                "  \"company\": {\n" +
                "    \"name\": \"Romaguera-Crona\",\n" +
                "    \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "    \"bs\": \"harness real-time e-markets\"\n" +
                "  }\n" +
                "}";

        Response response = given().
                headers("Content-Type", ContentType.JSON).
                body(myjson)
                .when()
                .post("/users").then().contentType(ContentType.JSON).extract().response();
        int statusCode = response.statusCode();
        assertEquals(201, statusCode);

        System.out.println("response>" + response.body().asString());

        String jsonResponse = response.jsonPath().getString("id");
        assertEquals("11", jsonResponse);

        Map<String, String> address = response.jsonPath().getMap("address");
        System.out.println(address.get("street"));

    }

    @Test
    public void testBasicAuth() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        String payload = "{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"password123\",\n" +
                "}";

        Response response = given().
                headers("Content-Type", ContentType.JSON).
                body(payload).
                when().post("/auth").
                then().contentType(ContentType.TEXT).extract().response();
        int statusCode = response.statusCode();
        assertEquals(200, statusCode);

        System.out.println("response>" + response.body().asString());
    }

    @Test
    public void testBasicAuthentication() {


        baseURI = "https://bookstore.toolsqa.com";

        String payload = "{\n" +
                "  \"userName\": \"TOOLSQA-Test\",\n" +
                "  \"password\": \"Test@@123\" \n" +
                "}";

        Response response = given()
                .headers("Content-Type", ContentType.JSON)
                .body(payload)
                .when().post("/Account/v1/GenerateToken")
                .then().contentType(ContentType.JSON).extract().response();
        int statusCode = response.statusCode();
        assertEquals(200, statusCode);
        String token = response.jsonPath().getString("token");
        assertNotNull(token);
        String result = response.jsonPath().getString("result");
        System.out.println("result: " + result);
    }

}
