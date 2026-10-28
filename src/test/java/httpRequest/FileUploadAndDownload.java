package httpRequest;

import java.io.File;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class FileUploadAndDownload {
	
	@Test
	void uploadSingleFile() {
		
		File file = new File("C:\\Users\\Techbit\\OneDrive\\Documents\\testdoc.txt");
		
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
		
		File file = new File("C:\\Users\\Techbit\\OneDrive\\Documents\\testdoc.txt");
		File file1 = new File("C:\\Users\\Techbit\\OneDrive\\Documents\\testdoc1.txt");
		
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
			.post("https://jsonplaceholder.typicode.com/posts")
			
		.then()
			.statusCode(201)
			.log().all();
		
	}

}
