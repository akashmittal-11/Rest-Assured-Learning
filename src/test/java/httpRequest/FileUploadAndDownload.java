package httpRequest;

import java.io.File;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class FileUploadAndDownload {
	
//	@Test
	void uploadSingleFile() {
		
		File file = new File("C:\\Users\\Techbit\\Documents\\Akash\\RestAssured\\Uploads\\testdoc.txt");
		
		given()
			.multiPart("file",file)
			.contentType("multipart/form-data")
		.when()
			.post("https://jsonplaceholder.typicode.com/posts")
			
		.then()
			.statusCode(201)
			.log().all();
		
	}

	@Test
	void uploadMultipleFile() {
		
		File file = new File("C:\\Users\\Techbit\\Documents\\Akash\\RestAssured\\Uploads\\testdoc.txt");
		File file1 = new File("C:\\Users\\Techbit\\Documents\\Akash\\RestAssured\\Uploads\\testdoc1.txt");
		
		// by using array we can upload the files as well
		
/*		File fileArr[] = {file,file1};
		given()
		.multiPart("files",fileArr)
		.contentType("multipart/form-data")
	*/	
		given()
			.multiPart("files",file)
			.multiPart("files",file1)
			.contentType("multipart/form-data")
			
		.when()
//			.post("https://jsonplaceholder.typicode.com/posts")
			.post("https://tempfile.org/api/upload/local")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	String url;
//	@Test(priority=1)
	void getDownloadUrl() {
		
		File file = new File("C:\\Users\\Techbit\\Documents\\Akash\\RestAssured\\Uploads\\testdoc.txt");

		Response res = given()
			.multiPart("files",file)
			.contentType("multipart/form-data")
			
		.when()
			.post("https://tempfile.org/api/upload/local");
		
	     url  = res.jsonPath().get("files[0].url").toString();
		
		System.out.println("Response is: "+url);		
	}
	
//	@Test(priority=2)
	void downloadFile()
	{
	given()

	.when()
		.get(url)
	
	.then()
		.statusCode(200)
		.log().all();
	}

}
