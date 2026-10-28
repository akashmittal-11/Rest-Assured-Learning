package httpRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;

public class JSONValidations {
	
	@Test
	void validateByThen() {
		
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("https://api.restful-api.dev/objects")
		
		.then()
			.statusCode(200)
			.body("[12].name",equalTo("Apple iPad Air"));
			
	
	}
	
	@Test
	void validateByAsserstions() {
		
		Response res=
		
		given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("https://api.restful-api.dev/objects");
		
		int statusCode = res.getStatusCode();
		String contentType = res.getContentType();
		String name = res.jsonPath().get("[12].name").toString();
		
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(contentType, "application/json;charset=UTF-8");
		Assert.assertEquals(name, "Apple iPad Air");
	
	}
	
	@Test
	void validationByJsonPath() {
		
		Response res=
				
				given()
					.contentType(ContentType.JSON)
				
				.when()
					.get("https://api.restful-api.dev/objects");
		
				String deviceName = res.jsonPath().get("[12].name").toString();	
				Assert.assertEquals(deviceName, "Apple iPad Air");
	}
	
	@Test(priority=1)
	void validationByJsonArray() {
		
		boolean status = false;
		
		Response res=
				
				given()
					.contentType(ContentType.JSON)
				
				.when()
					.get("https://api.restful-api.dev/objects");
		
				String response = res.asString();

				System.out.println("Response Body: " + response);

				// Convert the response body into a JSON array
				JSONArray jsonArray = new JSONArray(response);

				System.out.println("Length of an array is: " + jsonArray.length());
				
				for(int i=0;i<jsonArray.length();i++) {
					
					String value = jsonArray.getJSONObject(i).get("name").toString();
					System.out.println(value);
					
					if(value.equals("Apple AirPods")) {
						status  = true;
						break;
					}
					
				}
				
				Assert.assertEquals(status, true);

				
	}
	
	


}
