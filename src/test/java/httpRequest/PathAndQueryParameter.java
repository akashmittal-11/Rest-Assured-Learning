package httpRequest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameter {
	
	
	@Test
	void pathAndQueryParam() {
		
		given()
			.pathParam("path", "users")
			.queryParam("limit", "1")
		
		.when()
			.get("https://reqres.in/agent/v1/{path}")
		
		.then()
			.statusCode(200)
			.log().all();
		
		
	}

}
