
import org.testng.annotations.Test;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;


public class Test2 {

    @Test
    public void Test2_1(){
        int day = 86400;
        when().
                get("https://status.encoding.com/status.php?format=json").
                then().
                statusCode(200).assertThat()
                .body("status", equalTo("Ok"))
                .body("incident_count.lastYear.toInteger()", lessThan(10))
                .body("uptime", greaterThan(day));
    }
    @Test
    public void Test2_2(){
        int day = 86400;
        when().
                get("https://status.encoding.com/status.php?format=xml").
                then().
                statusCode(200).assertThat()
                .body("response.status", equalTo("Ok"))
                .body("response.incident_count.lastYear.toInteger()", lessThan(10))
                .body("response.uptime.toInteger()", greaterThan(day));
    }
}
