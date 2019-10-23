package javas.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface JagadishInterfae{
	public void doReferredWork();
}

@FunctionalInterface
interface ConstructorMethodReference{
	public abstract Java8Tests getInstance();
}

public class Java8Tests {
	Java8Tests(){
		System.out.println("TestClass.TestClass() CONSTUCTOR");
	}
	
	public static void testDisplayStatic() {
		System.out.println("One");
		System.out.println("Two");
		System.out.println("Three");
	}
	
	public void testDisplayInstance() {
		System.out.println("One Instance");
		System.out.println("Two Instance");
		System.out.println("Three Instance");
	}
	
	
	
	public static void main(String[] args) {
		
//FUNCTIONAL INTERFACES
		//PREDICATE (Special Case of Function...)
		Predicate<Integer> isLess = (I)-> I < 10;
		Predicate<Integer> isEven = (I)-> I % 2==0;
		Predicate<String> isJagadish = Predicate.isEqual("JAGADISH");
		
		int INTS = 6;
		System.out.println(isLess.test(INTS));
		System.out.println(isEven.test(INTS));
		System.out.println(isLess.and(isEven).test(INTS));
		System.out.println(isLess.or(isEven).test(INTS));
		System.out.println(isLess.negate().test(INTS));
		System.out.println(isJagadish.test("JAGADISH"));
		
		//FUNCTION
		Function<String, String> identityFunction = Function.identity();
		Function<Integer, Integer> multiplyFunction= (I)->I*I;
		Function<Integer, Integer> addFunction= (I)->I+I;
		Function<List<String>, Integer> totalLength = (LIST)->{ 
			int leng=0;
			for(String each: LIST) {
				leng = leng+each.length();
			}
			return leng;
		};
		List<String> animals = new ArrayList<String>();
		animals.add("Rhino"); animals.add("Tiger");
		
		System.out.println(multiplyFunction.apply(5));
		System.out.println(totalLength.apply(animals));
		System.out.println(multiplyFunction.andThen(addFunction).apply(2)); //f1 then f2
		System.out.println(multiplyFunction.compose(addFunction).apply(2)); //f2 then f1
		System.out.println(identityFunction.apply("JOGA") == "JOGA");
		
		//CONSUMER
		Consumer<List<String>> consumer = (LIST)->{ LIST.forEach(e->System.out.println(e));	};
		List<String> movieList = new ArrayList<String>();
			movieList.add("Rambo");
			movieList.add("Gandhi");
		consumer.accept(movieList);
		//Consumer Chaining.
		Consumer<String> movie1 = (s)->System.out.print("Movie is Released:"+s);
		Consumer<String> movie2 = (s)->System.out.print(" >Movie is Hit:"+s);
		Consumer<String> movie3 = (s)->System.out.println(" >Movie is Closed:"+s);
		movie1.andThen(movie2).andThen(movie3).accept("Bahubalid");
		
		//SUPPLIER
		Supplier<String> supplier = ()->Math.random()+"";
		System.out.println(supplier.get());
		
//METHOD References
		//Static method references.
		JagadishInterfae myFuncInterface = Java8Tests::testDisplayStatic;
		myFuncInterface.doReferredWork();
		//Instance method references.
		myFuncInterface = new Java8Tests()::testDisplayInstance;
		myFuncInterface.doReferredWork();
		//Constructor reference.
		ConstructorMethodReference methodRefere= Java8Tests::new;
		System.out.println(methodRefere.getInstance());
		
		
//DATE TIME API ================
		System.out.println(LocalDate.now().getMonthValue());
		System.out.println(LocalTime.now());
		System.out.println(LocalDateTime.now());
		
		LocalDateTime customDateTime = LocalDateTime.of(2019,Month.MAY,28, 15,15,15,15);
		System.out.println(customDateTime);
		System.out.println(customDateTime.plusDays(3));
		//PERIOD
		LocalDate birthDay = LocalDate.of(1987,Month.JULY,12);
		LocalDate today = LocalDate.now();
		Period p = Period.between(birthDay, today);
		System.out.println("YEARS:"+p.getYears()+" MONTHS:"+p.getMonths()+" DAYS:"+p.getDays());
		Year y = Year.of(2000);
		System.out.println("Year is Leap: "+y.isLeap());
		Month m = Month.MAY;
		System.out.println(m);
		//ZONE
		System.out.println(ZoneId.systemDefault());
		ZoneId zone = ZoneId.of("Asia/Calcutta");
		ZonedDateTime dt = ZonedDateTime.now(zone);
		System.out.println(dt);
		System.out.println("//------------------------");
		Map<Student, Student> studentSet = new HashMap<Student, Student>(1,0.1f);
		for(int i=0;i<20;i++) {
			//studentSet.put(new Student(i), new Student(i));
		}
		
		/*
		studentSet.add(new Student(1));
		studentSet.add(new Student(2));
		studentSet.add(new Student(3));
		studentSet.add(new Student(4));
		studentSet.add(new Student(5));
		studentSet.add(new Student(6));
		studentSet.add(new Student(7));
		studentSet.add(new Student(8));
		studentSet.add(new Student(9));
		studentSet.add(new Student(10));
		studentSet.add(new Student(11));
		studentSet.add(new Student(12));
		studentSet.add(new Student(13));
		studentSet.add(new Student(14));
		studentSet.add(new Student(15));
		*/
		
		List<String> helloList = new ArrayList<String>();
		helloList.forEach(System.out::print);
		
		
	}
}

class Student implements Comparator<Integer>{
	private int sid=0;
	private int rollNo;
	private String sname;
	
	public Student(int rollNo) {
		this.rollNo = rollNo;
	}
	@Override
	public String toString() {
		return "[SID"+this.sid+", SNAME"+this.sname+"]";
	}
	@Override
	public int hashCode() {
		System.out.println("Student.hashCode()");
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		sid++;
		for(int i=0;i<sid;i++) {
			System.out.print(i+",");
		}
		System.out.println("");
		return super.equals(obj);
	}
	@Override
	public int compare(Integer o, Integer p) {
		System.out.println("Student.compareTo()");
		return p-o;
	}
}
