package apiChaining;


import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


import org.json.JSONObject;

public class CreateUser {
	
	@Test
	void test_createUser() {
		
		Faker fake = new Faker();
		JSONObject productData = new JSONObject();
		JSONObject responseBody = new JSONObject();
		productData.put("year", fake.number().numberBetween(2000, 2026));
		productData.put("price", fake.number().randomDouble(1, 50000, 100000));
		productData.put("CPU model", "Intel Core i9");		
		productData.put("Hard disk size", fake.number().numberBetween(2, 17) + " TB");		
		responseBody.put("name", fake.name().firstName()+ "'s iPhone");
		responseBody.put("data", productData);

		given()
		
			.contentType("application/json")
			.body(responseBody.toString())
		.when()
		
			.post("https://api.restful-api.dev/objects")
		.then()
		
			.statusCode(200)
			.log().body();
		
	}}
