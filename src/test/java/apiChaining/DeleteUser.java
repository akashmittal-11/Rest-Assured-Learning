package apiChaining;


import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteUser {

	
	@Test
	void testDeleteUser(ITestContext context) {
		
//		String id = (String) context.getAttribute("object_id");
		String id = (String) context.getSuite().getAttribute("object_id");
		

		given()
			
			.pathParam("id", id)
			
		.when()
		
			.delete("https://api.restful-api.dev/objects/{id}")
		
		.then()
		
			.statusCode(200)
			.log().all();
		
		
	}
}
