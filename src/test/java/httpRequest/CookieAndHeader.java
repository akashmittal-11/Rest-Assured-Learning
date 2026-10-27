package httpRequest;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.Map;

public class CookieAndHeader {
	
//	@Test
	void getAllInfo() {
		
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC","Aaa9EJqa_m9TpBQovZevFqm7VABX-TV7LYtV1CgPzK2PrCSqq-GDD-guCA")
			.log().all();
		/*this test got fail it means it is working fine, on every execution of page new cookie is 
		getting generated so the assertion of cookie is getting failed.*/
	}
	
	
//	@Test
	void getSingleCookieInfo() {
		
		Response res= given()
		
		.when()
			.get("https://www.google.com/");
		
		String response=	res.getCookie("AEC");
		System.out.println("AEC Cookie is: " +response);
	}
	
//	@Test
	void getAllCookiesInfo() {
		
		Response res= given()
				
				.when()
					.get("https://www.google.com/");
		
		Map<String, String> keys = res.getCookies();
		
		for(String k : keys.keySet()) { //keySet() is a method is use to extract only keys
			
			String cookie_value=	res.getCookie(k);
			System.out.println("Cookie value of "+k+" is:  " +cookie_value);

		}
	}
	
//	@Test
void getSingleHeaderInfo() {
		
		Response res= given()
		
		.when()
			.get("https://www.google.com/");
		
		String response=	res.getHeader("Content-Type");
		System.out.println("Header is :  " +response);
	}
	
//	@Test
	void getAllHeadersInfo() {
		
		Response res= given()
				
				.when()
					.get("https://www.google.com/");
		
		Headers headers = res.getHeaders();
		
		for(Header head : headers) { 
		
			String header_value =	head.getValue();
			System.out.println("Header value of "+head.getName() +" is:-  " +header_value);

		}
	}
		
		
//		@Test
		void getheaders() {
			
			given()
			
			.when()
				.get("https://www.google.com/")
			
			.then()
				.header("Content-Type","text/html; charset=ISO-8859-1")
				.log().headers();
		}
	
		@Test
		void loggingMethods() {
			
			given()
			
			.when()
				.get("https://www.google.com/")
			
			.then()
			.log().all();		//it will log all data on the console
//			.log().body();		// it will log only body response
//			.log().headers();	//it will log only headers in the console
//			.log().cookies();	//it will log only cookies from the response
			
			
		}
	
	
	
	
}
