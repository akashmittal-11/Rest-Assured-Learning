package httpRequest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostRequestsMethods {
	
	String id;
	
//	@Test
	void postRequestByHashMapBody() {
		
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		requestBody.put("name", "Akash's iPhone 12");
		data.put("year", 2022);
		data.put("price", 58000);
		requestBody.put("data", data);
		
		given()
			.contentType("application/json")
			.body(requestBody)
		
		.when()
			.post("https://api.restful-api.dev/objects")
		
		.then()
			.statusCode(200)
			.body("name", equalTo("Akash's iPhone 12"))
			.log().all();
		
	}
	
//	@Test(priority=1)
	void postRequestByHashMapBodyAndGetID() {
		
		HashMap<String, Object> requestBody = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		requestBody.put("name", "Akash's iPhone 12");
		data.put("year", 2022);
		data.put("price", 58000);
		requestBody.put("data", data);
		
		id=given()
			.contentType("application/json")
			.body(requestBody)
		
		.when()
			.post("https://api.restful-api.dev/objects")
			.jsonPath().getString("id");
		
	}
	
	
//	@Test(priority=2,dependsOnMethods = {"postRequestByHashMapBodyAndGetID"})
	void getRequest() {
		
		when()
			.get("https://api.restful-api.dev/objects/"+id)
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
//	@Test
	void postRequestByJSONLibrary() {
		
		JSONObject requestBody = new JSONObject();
		JSONObject data = new JSONObject();
		requestBody.put("name", "Akash's iPhone 12");
		data.put("year", 2022);
		data.put("price", 58000);
		requestBody.put("data", data);
		
		given()
			.contentType("application/json")
			.body(requestBody.toString())
		
		.when()
			.post("https://api.restful-api.dev/objects")
		
		.then()
			.statusCode(200)
			.body("name", equalTo("Akash's iPhone 12"))
			.log().all();
	}
	
//	@Test(priority = 1)
	void postRequestByJSONLibraryAndGetID() {
		
		JSONObject requestBody = new JSONObject();
		JSONObject data = new JSONObject();
		requestBody.put("name", "Akash's iPhone 12");
		data.put("year", 2022);
		data.put("price", 58000);
		requestBody.put("data", data);
		
		id=given()
			.contentType("application/json")
			.body(requestBody.toString())
		
		.when()
			.post("https://api.restful-api.dev/objects")
			.jsonPath().getString("id");
		
		}
	
//	@Test(priority=2,dependsOnMethods = {"postRequestByJSONLibraryAndGetID"})
	void deleteObject() {
		
		when()
			.delete("https://api.restful-api.dev/objects/"+id)
		
		.then()
			.statusCode(200);		
	}
	
//	@Test
	void postRequestByPOJO() {
		
		ProductData data = new ProductData(2021, 52000);
		PojoClass requestBody = new PojoClass("iphone 12", data);
		
		given()
			.contentType("application/json")
			.body(requestBody)
		.when()
		.post("https://api.restful-api.dev/objects")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void postRequestByJsonFile() throws FileNotFoundException {
		
		File f = new File(".//product.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		given()
		.contentType("application/json")
		.body(data.toString())
	
	.when()
		.post("https://api.restful-api.dev/objects")
	
	.then()
		.statusCode(200)
		.body("name", equalTo("iPhone 12"))
		.log().all();
		
	}	

}
