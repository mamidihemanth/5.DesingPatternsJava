package A_creational;

class Person {

	private final String name;
	private final String email;
	private final String address;
	private final int age;
	private final String nameOfMother;
	
	public Person(String name, String email, String address, int age, String nameOfMother) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.age = age;
		this.nameOfMother = nameOfMother;
	}
	
	public Person(Builder builder){
		this.name = builder.name;
		this.email = builder.email;
		this.address = builder.address;
		this.age = builder.age;
		this.nameOfMother = builder.nameOfMother;
	}
	
	//Static Class
	public static class Builder {
		
		private final String name;
		private final String email;
		private String address;
		private int age;
		private String nameOfMother;
		
		public Builder(String name, String email){
			this.name = name;
			this.email = email;
		}
		
		public Builder setAddress(String address){
			this.address = address;
			return this;
		}
		
		public Builder setNameOfMother(String nameOfMother){
			this.nameOfMother = nameOfMother;
			return this;
		}
		
		public Builder setAge(int age){
			this.age = age;
			return this;
		}
		
		public Person build(){
			return new Person(this);
		}
	}
	
	@Override
	public String toString() {
		return this.name+" : "+this.address+" : "+this.email+" : "+this.age;
	}
}


public class E_BuilderPattern {
	public static void main(String[] args) {
		System.err.println("Description: The parameters to the constructor are reduced and are provided in highly readable method calls.");
		Person person0 = new Person("Jagadish", "Jai@gmail.com", "Hyderabad", 24, "Mom");
		System.out.println(person0);
		
		Person person1 = new Person.Builder("Balazs", "balazs@gmail.com").build();
		System.out.println(person1);
		
		Person person2 = new Person.Builder("Balazs", "balazs@gmail.com")
									.setNameOfMother("Manikyam")
									.setAddress("Gopalpur").build();
		System.out.println(person2);
	}
}
