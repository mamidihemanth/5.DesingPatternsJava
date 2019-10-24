package javas.java8;

import java.util.Optional;

public class OptionalTest {

	public static void whenCreatesEmptyOptional_thenCorrect() throws Exception {
		Optional.empty().isPresent();
		Optional<String> opt = Optional.of("baeldung");
		// Optional.of(null); //Throws Exception
		opt = Optional.ofNullable("baeldung");
		opt = Optional.ofNullable(null);
		String name = Optional.ofNullable((String) null).orElseGet(() -> "john");
		//Optional.ofNullable((String) null).orElseThrow(()->new RuntimeException());
		 Optional<String> nameOptional = Optional.of("baeldung");
		 int size = nameOptional.map(each->each.length()).orElse(0);
		 //===============
		 StudentExam stdExam = new StudentExam(26 , "john");
		 Optional<StudentExam> examOptional = Optional.of(stdExam);
		 System.out.println(examOptional.flatMap(each->each.getRollNo()));
		 System.out.println(size);
	}

	public static void main(String[] args) throws Exception {
		whenCreatesEmptyOptional_thenCorrect();
	}
}

class StudentExam{
	private int rollNo;
	private String name;

	public StudentExam(int rollNo, String name) {
		super();
		this.rollNo = rollNo;
		this.name = name;
	}
	
	public Optional<Integer> getRollNo() {
		return Optional.ofNullable(rollNo);
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}
	public void setName(String name) {
		this.name = name;
	}
}
