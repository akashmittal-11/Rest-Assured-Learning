package serializationAndDeserialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerilizationAndDeserilization {

	@Test
	void convertJavaToJson() throws JsonProcessingException { //serilization
		
		StudentPojoClass studPojo = new StudentPojoClass();
		studPojo.setName("Akash Mittal");
		studPojo.setAddress("Mohali");
		studPojo.setAge(25);
		studPojo.setPincode(140308);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonValue = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studPojo);
		System.out.println("Json response is: "+jsonValue);
	}
	
	@Test
	void convertJsonToJava() throws JsonProcessingException { //deserialization
		
		String json = "{\r\n"
				+ "  \"name\" : \"Akash Mittal\",\r\n"
				+ "  \"address\" : \"Mohali\",\r\n"
				+ "  \"age\" : 25,\r\n"
				+ "  \"pincode\" : 140308\r\n"
				+ "}";
		
		ObjectMapper objectMapper = new ObjectMapper();
		StudentPojoClass stu = objectMapper.readValue(json, StudentPojoClass.class);
		System.out.println(stu.getName());
		System.out.println(stu.getAge());
		System.out.println(stu.getPincode());
		System.out.println(stu.getAddress());
	}
	
	
	
}
