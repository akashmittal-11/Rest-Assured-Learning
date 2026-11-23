package apiChaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static io.restassured.RestAssured.*;

public class UpdateUser {
	
	@Test
	void testUpdateUser(ITestContext context) {
			
		Faker fake = new Faker();
		JSONObject productData = new JSONObject();
		JSONObject responseBody = new JSONObject();
		productData.put("year", fake.number().numberBetween(2000, 2026));
		productData.put("price", fake.number().randomDouble(1, 50000, 100000));
		productData.put("CPU model", "Intel Core i9");		
		productData.put("Hard disk size", fake.number().numberBetween(2, 17) + " TB");		
		responseBody.put("data", productData);
//		String id = (String) context.getAttribute("object_id");
		String id = (String) context.getSuite().getAttribute("object_id");

		
		given()
		
			.contentType("application/json")
			.body(responseBody.toString())
			.pathParam("id", id)
			
		.when()
		
			.patch("https://api.restful-api.dev/objects/{id}")
		.then()
		
			.statusCode(200)
			.log().body();
	}

}
