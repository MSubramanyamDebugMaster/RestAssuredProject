import Pojos.UserDTO;
import io.restassured.RestAssured;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class Test {
    UserDTO userdto;
    @BeforeClass
    public void setup()
    {
        RestAssured.baseURI="https://petstore.swagger.io/v2";

    }
    @org.testng.annotations.Test(priority = 0)
    public void createUserByUserName()
    {
         userdto=UserDTO.builder().id(1).username("Subbu").firstName("M").lastName("Subramanyam")
                .email("msubbu@3208").password("Hekl123").phone("8178474534").userStatus(1).build();
        RestAssured.given().log().all().header("Content-Type","application/json").body(userdto)
                .when().post("/user").then().log().all().statusCode(200);
        userdto.setUsername("Subbu");
    }
    @org.testng.annotations.Test(priority = 1)

    public void getUserByUser()
    {

        String ActualUsrerName = RestAssured.given().log().all().pathParam("username", userdto.getUsername()).when().get("/user/{username}/")
                .then().log().all().statusCode(200).extract().response().jsonPath().get("username").toString();
        Assert.assertEquals(ActualUsrerName,userdto.getUsername());

    }
}
