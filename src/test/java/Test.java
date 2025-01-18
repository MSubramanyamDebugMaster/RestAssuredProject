import Pojos.Helper;
import Pojos.UserDTO;
import io.restassured.RestAssured;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class Test {
    UserDTO userdto;
    Helper helper;
    @BeforeClass
    public void setup()
    {
        RestAssured.baseURI="https://petstore.swagger.io/v2";
        helper=new Helper();
    }
    @org.testng.annotations.Test(priority = 0)
    public void createUserByUserName()
    {
         userdto=UserDTO.builder().id(1).username("Subbu").firstName("M").lastName("Subramanyam")
                .email("msubbu@3208").password("Hekl123").phone("8178474534").userStatus(1).build();
        UserDTO response=RestAssured.given().log().all().header("Content-Type","application/json").body(userdto)
                .when().post("/user").then().log().all().statusCode(200).extract().response().as(UserDTO.class);
        userdto.setUsername("Subbu");
        helper.setHelper(response);
    }
    @org.testng.annotations.Test(priority = 1)

    public void getUserByUser()
    {

        String ActualUsrerName = RestAssured.given().log().all().pathParam("username", userdto.getUsername()).when().get("/user/{username}/")
                .then().log().all().statusCode(200).extract().response().jsonPath().get("username").toString();
        Assert.assertEquals(ActualUsrerName,userdto.getUsername());

    }
    @org.testng.annotations.Test(priority = 2)
    public void updateUser()
    {
        userdto=UserDTO.builder().id(1).username("Rupam").firstName("M").lastName("Subramanyam")
                .email("msubbu@3208").password("Hekl123").phone("8178474534").userStatus(1).build();
        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("username", helper.getHelper().getUsername()) // Add path param for username
                .body(userdto)
                .when()
                .put("/user/{username}")
                .then().statusCode(200)
                .log().all().extract().response().jsonPath().get("username");

    }
    @org.testng.annotations.Test(priority = 3)
    public void deleteUser() {
        RestAssured.given()
                .log().all()
                .pathParam("username", userdto.getUsername())
                .when()
                .delete("/user/{username}/")
                .then()
                .log().all()
                .statusCode(200);
    }
}
