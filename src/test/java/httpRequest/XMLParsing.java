package httpRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class XMLParsing {

	@Test
	void readAndValidateXMLResponse() {
		
		given()
		
		.when()
			.get("https://gorest.in/public/v2/users.xml?utm_source=chatgpt.com")
		
		.then()
			.statusCode(200)
			.body("users.user[0].name",equalTo("Aarav Sharma"));	
		
	}
	
	
	@Test
	void validateResponseByXMLPath() {
		
		Response res = 
		given()
		
		.when()
			.get("https://gorest.in/public/v2/users.xml?utm_source=chatgpt.com");
		
		int statusCode = res.getStatusCode();
		String cityName = res.xmlPath().get("users.user[0].name").toString();
		Assert.assertEquals(statusCode,200 );
		Assert.assertEquals(cityName,"Aarav Sharma" );
	}
	
	@Test
	void getXMLListing() {
		
		Response res=
		given()
		
		.when()
			.get("https://gorest.in/public/v2/users.xml?utm_source=chatgpt.com");
		
		XmlPath xmlObj = new XmlPath(res.asString());
		
		//how to get list of all nodes
		List<String> users = xmlObj.getList("users.user");

		int userList = users.size();
		
		Assert.assertEquals(userList, 10);
		
	}
	
	@Test
	void validateXMLResponseUsingSearching() {
		
		Response res=
		given()
		
		.when()
			.get("https://gorest.in/public/v2/users.xml?utm_source=chatgpt.com");
		
		XmlPath xmlObj = new XmlPath(res.asString());
		
		List<String> names = xmlObj.getList("users.user.name");
		
		boolean status = false;
		for(String name:names)
		{
			
			if(name.equals("Kavya Reddy")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
	}
	
	
}
