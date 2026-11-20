package apiChaining;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetUser {
	
	
	@Test
	void test_getUser() {
		
		given()
			.pathParam("id","ff808181a09d98f701a1f8eec51907c5" )
		.when()
			.get("https://api.restful-api.dev/objects/{id}")
			
		
		.then()
			.statusCode(200)
			.log().body();
		
	}

}
