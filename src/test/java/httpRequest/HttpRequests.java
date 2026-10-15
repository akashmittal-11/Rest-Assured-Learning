package httpRequest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {
	
	int id;
	
	
//	@Test
	void getRequest() {
		
		when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
//	@Test
	void postRequest() {
		
		HashMap data = new HashMap();
		data.put("name","Akash");
		data.put("Age", 25);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
		
		.then()
			.statusCode(201)
			.log().all();
	}
	
//	id = 261
	
	
	@Test(priority = 1)
	void createUserAndGetID() {
		
		HashMap data = new HashMap();
		data.put("name","Akash");
		data.put("Age", 25);
		
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
	}
	@Test(priority=2,dependsOnMethods = {"createUserAndGetID"})
	void putRequest() {
		HashMap data = new HashMap();
		data.put("name","Akash Mittal");
		data.put("Age", 27);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)
		
		.then()			
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 3,dependsOnMethods = {"putRequest"})
	void deleteRequest() {
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}

}
