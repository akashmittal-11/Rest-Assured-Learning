package httpRequest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests2 {
	
	String id;
	
//	@Test
	void getRequest() {
		
		when()
			.get("https://api.restful-api.dev/objects")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void createObject() {
		
		HashMap data = new HashMap();
		data.put("name", "iPhone 12");
		data.put("Storage", "128 GB");
		data.put("Color", "Black");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://api.restful-api.dev/objects")
			
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(priority = 1)
	void createObjectAndGetID() {
		
		HashMap data = new HashMap();
		data.put("name", "iPhone 12");
		data.put("Storage", "128 GB");
		data.put("Color", "Black");
		
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://api.restful-api.dev/objects")
			.jsonPath().getString("id");
		
	}
	
	@Test(priority = 2,dependsOnMethods = {"createObjectAndGetID"})
	void updateObject() {
		
		HashMap data = new HashMap();
		data.put("name", "iPhone 13");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://api.restful-api.dev/objects/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 3,dependsOnMethods = {"createObjectAndGetID"})
	void deleteObject() {
		
		when()
			.delete("https://api.restful-api.dev/objects/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
	

}
