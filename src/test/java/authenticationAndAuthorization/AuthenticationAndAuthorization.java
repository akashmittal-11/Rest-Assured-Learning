package authenticationAndAuthorization;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class AuthenticationAndAuthorization {
	
	
	@Test
	void basicAuthentication() {
		
		given()
//			.auth().basic("user", "passwd")
//			.auth().digest("user", "passwd")
			.auth().preemptive().basic("user", "passwd")
		
		.when()
			.get("https://httpbin.org/basic-auth/user/passwd")
		
		.then()
			.body("authenticated", equalTo(true))
			.statusCode(200);
			
	}
	
	
	@Test
	void bearerAuthentication()
	{
		String token = "myTestToken123";
		given()
			.headers("Authorization", "Bearer "+token)
		.when()
			.get("https://httpbin.org/bearer")
		.then()
			.body("authenticated", equalTo(true))
			.statusCode(200);
	}
	
	
	@Test
	void oauth2Authorization() {
		
		// get oauth token and URL from ---> https://testingapis.com/docs/oauth/protected/
		
		String token = "oat_d9c8eb037413770ab4f183a2f903cb79ba635b69";
		given()
			.auth().oauth2(token)
		.when()
			.get("https://api.testingapis.com/api/auth/oauth/protected")
		.then()
			.body("message", equalTo("You accessed an OAuth-protected resource."))

			.statusCode(200);
	}
	
	
	@Test
	void apiKeyAuthentication() {
		
		// get oauth token and URL from ---> https://testingapis.com/docs/oauth/protected/
		
		String token = "oat_d9c8eb037413770ab4f183a2f903cb79ba635b69";
		given()
			.auth().oauth2(token)
		.when()
			.get("https://api.testingapis.com/api/auth/oauth/protected")
		.then()
			.body("message", equalTo("You accessed an OAuth-protected resource."))

			.statusCode(200);
	}
	
	
	

}


