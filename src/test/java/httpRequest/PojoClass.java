package httpRequest;

public class PojoClass {
	
	String name;
	ProductData data;
	
	public PojoClass(String name, ProductData data) {
		
		this.name = name;
		this.data = data;
		
	}
	public String getName() {
		return name;
	}
	
	public ProductData getData() {
		return data;
	}
	

}
