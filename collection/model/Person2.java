package ir.freeland.collection.model;

public class Person2 implements Comparable<Person2>{
	
	private String FirstName;
    private String lastName;
    private int age;
    private Address address;
    
	public Person2(String firstName, String lastName, int age, Address address) {
		super();
		FirstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
	}
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int compareTo(Person2 o) {
		return this.age - o.age;
	}

	@Override
	public String toString() {
		return "Person2 [FirstName=" + FirstName + ", lastName=" + lastName + ", age=" + age + ", address=" + address
				+ "]";
	}
    
    
}
