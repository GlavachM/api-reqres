package reqres;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import io.restassured.matcher.RestAssuredMatchers.*;

/**
 * Created by Mykola on 14.09.2019.
 */
public class tests {

    @Test
    public void getUserList() {
        given().
                when().
                get("https://reqres.in/api/users?page=2")
                .then()
                .body("page",equalTo(2),
                      "per_page", equalTo(6),
                      "total", equalTo(12),
                      "total_pages", equalTo(2),
                      "data.id",hasItems(7, 8, 9, 10, 11, 12))
                .statusCode(200);
    }

    @Test
    public void getSingleUser(){
        given().
                when().
                get("https://reqres.in/api/users/2")
                .then()
                .body("data.id",equalTo(2),
                      "data.email", equalTo("janet.weaver@reqres.in"),
                      "data.first_name", equalTo("Janet"),
                      "data.last_name", equalTo("Weaver"),
                      "data.avatar",equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"))
                .statusCode(200);
//                .extract().body().jsonPath()
//                .prettyPrint();
    }

    @Test
    public void getSingleUserNotFound(){
        given().
                when().
                get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void createUser()
    {
        given().
                contentType("application/json").
                queryParam("name", "morpheus").
                queryParam("job", "leader").
        when().
                post("https://reqres.in/api/users")
        .then()


                .body("createdAt",containsString("2019-09-14"),
                      "id", is(notNullValue()))
                .statusCode(201);
    }

    @Test
    public void putUser(){
        given().
                contentType("application/json").
                queryParam("name", "morpheus").
                queryParam("job", "leader").
        when().
                put("https://reqres.in/api/users/2")
        .then()
                .body("updatedAt", containsString("2019-09-14"));
    }
}
