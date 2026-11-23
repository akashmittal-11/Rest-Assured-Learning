package apiChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	
	@Test
	void test_getUser(ITestContext context) {
		
//		String id = (String) context.getAttribute("object_id");
		String id = (String) context.getSuite().getAttribute("object_id");

		System.out.println("id is: "+id);
		given()
			.pathParam("id",id )
		.when()
			.get("https://api.restful-api.dev/objects/{id}")
			
		.then()
			.statusCode(200)
			.log().body();
		
	}

}
