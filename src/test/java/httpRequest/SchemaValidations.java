package httpRequest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.internal.matcher.xml.XmlXsdMatcher;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaValidations {
	
	
	
	
	/*
	 * Shcema validator is not validation of data 
	 * It is validation of the data type that available in the repsonse
	 * So, first we have to create a json schema of the json response  --> https://transform.tools/json-to-json-schema
	 * store in the json file 
	 * store that file in the src/test/resources folder
	 * then apply assertion on the schema to validate the response
	 * */
	
//	@Test
	void jsonSchemaValidation() {
		
		given()
		
		.when()
		
			.get("https://reqres.in/api/users?page=2")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
	}
	
	/*
	 * Shcema validator is not validation of data 
	 * It is validation of the data type that available in the repsonse
	 * So, first we have to create a xml schema of the xml response  --> https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/
	 * store in the xsd file 
	 * store that file in the src/test/resources folder
	 * then apply assertion on the schema to validate the response
	 * */
	@Test
	void xmlSchemaValidation() {
		
		given()
		
		.when()
		
			.get("https://gorest.in/public/v2/users.xml?utm_source=chatgpt.com")
		
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
	}
	
	

}
