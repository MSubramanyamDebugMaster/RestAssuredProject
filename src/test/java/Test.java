import Pojos.UserDTO;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class Test {
    @BeforeClass
    public void setup()
    {
        RestAssured.baseURI="https://petstore.swagger.io/v2";

    }
    @org.testng.annotations.Test
    public void createUserByUserName()
    {
        UserDTO userdto=UserDTO.builder().id(1).username("Subbu").firstName("M").lastName("Subramanyam")
                .email("msubbu@3208").password("Hekl123").phone("8178474534").userStatus(1).build();
        RestAssured.given().log().all().header("Content-Type","application/json").body(userdto)
                .when().post("/user").then().log().all().statusCode(200);
        userdto.setUsername("Subbu");
    }
}
