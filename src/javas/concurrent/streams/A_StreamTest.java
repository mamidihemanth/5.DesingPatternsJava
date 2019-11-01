package javas.concurrent.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class A_StreamTest {
	/*
	Note: We can't reuse Streams. (StreamSupport, Spliterator), but convert Iterator to stream and re use.
	Stream Types: Stream<T>, IntStream, LongStream, DoubleStream

	Stream Operations:
		1. Intermediate: sorted, reduce
			a. Sort Circuit: limit, skip..etc.
		2. Terminal: collect, forEach
	
	Note: Stream of elements can't be modified.
	*/
	public static void main(String[] args) {
		List<Integer> nameList = new ArrayList<Integer>();
		nameList.add(2);
		nameList.add(1);
		nameList.add(5);
		nameList.add(5);
		// FIlter
		System.out.println(nameList.stream().filter(each -> each == 2).collect(Collectors.toList()));
		// Map
		System.out.println(nameList.stream().map(each -> "-" + each + "-").collect(Collectors.toList()));
		// mapToInt
		// mapToLong
		// flatMap
		// distinct
		System.out.println(nameList.stream().distinct().collect(Collectors.toList()));
		// sorted   ##short-circuiting cant to applied to it.
		System.out.println(
				"SORTEED : " + nameList.stream().sorted((a, b) -> -a.compareTo(b)).collect(Collectors.toList()));
		// Peek
		nameList.stream().peek((e) -> {
			System.out.println(e);
		});
		// limit
		System.out.println("LIMIT : " + nameList.stream().limit(2).collect(Collectors.toList()));
		// skip
		System.out.println("SKIP : " + nameList.stream().skip(1).collect(Collectors.toList()));
		// ForEach
		nameList.stream().forEach((e) -> {
			System.out.print(e + " ");
		});
		// ForEachOrdered
		System.out.println("\nForEachOrdered :");
		nameList.stream().forEachOrdered((e) -> {
			System.out.print(e + " ");
		});

		// toArray
		System.out.println("\ntoArray :");
		Object[] listArr = nameList.toArray();
		for (Object e : listArr) {
			System.out.print(e + " - ");
		}
		// reduce
		System.out.println("\nREDUCE : "+nameList.stream().reduce(0, (subtotal, element) -> {System.out.println(subtotal); return subtotal + element;}));
		// nameList.stream().reduc
		// min
		System.out.println("\nMIN : " + nameList.stream().min((a, b) -> a.compareTo(b)));
		// max
		System.out.println("MAX : " + nameList.stream().max((a, b) -> a.compareTo(b)));
		// count
		System.out.println("count : " + nameList.stream().count());
		// anyMathc
		System.out.println("anyMathc : " + nameList.stream().anyMatch(each -> each == 100));
		// allMatch
		System.out.println("allMatch : " + nameList.stream().allMatch(each -> each > 0));
		// noneMatch
		System.out.println("noneMatch : " + nameList.stream().noneMatch(each -> each > 0));
		// findFirst
		System.out.println("findFirst : " + nameList.stream().findFirst().orElse(null));
		// findAny
		System.out.println("findAny : " + nameList.stream().findAny().get());
		// of

		// of
		IntStream stream = "12345_abcdefg".chars();
		stream.forEach(p -> System.out.print((char) p));
		System.out.println("\n=================");
		Stream<String> streams = Stream.of("A$B$C".split("\\$"));
		streams.forEach(p -> System.out.print(p));
		System.out.println();
		List<String> list1 = Stream.of("11", "12", "11113", "116", "345").collect(Collectors.toList());
		System.out.println(list1);
		IntStream strInts = IntStream.of(1, 2, 4, 5, 6);
		strInts.forEach(System.out::print);
		System.out.println();
		// empty
		Stream<String> emptyStream = Stream.empty();
		emptyStream.forEach(System.out::println);
		// iterate
		Stream.iterate(0, n -> n + 1).limit(10).forEach(e -> System.out.print(e));
		// Generate
		System.out.println();
		System.out.println("GENERATE: ");
		Stream.generate(new Random()::nextInt).limit(5).forEach(e -> System.out.print(e + " - "));
		// Concat
		Stream<Integer> firstStream = Stream.of(1, 2, 3);
		Stream<Integer> secondStream = Stream.of(4, 5, 6);
		System.out.println();
		Stream<Integer> resultingStream = Stream.concat(firstStream, secondStream);
		System.out.println("CONCAT : " + resultingStream.collect(Collectors.toList()));

		// Stream of Primitive Types
		System.out.println("====STREAMS OF PRIMITIVE TYPES====");
		IntStream.range(1, 4).forEach(System.out::print);
		System.out.println();
		System.out.println("=============================");
		Arrays.stream(new int[] { 1, 2 }).map(n -> n * n).average().ifPresent(System.out::println);
		System.out.println("=============================");
		Stream.of(1.6, 2.3).mapToInt(Double::intValue).forEach(System.out::print);
		System.out.println("\n=============================");
		
		//Generating Streams:
		Employee[] arrayOfEmps = {
			    new Employee(1, "Jeff Bezos", 100000.0), 
			    new Employee(2, "Bill Gates", 200000.0), 
			    new Employee(3, "Mark Zuckerberg", 300000.0)
			};
		//Infinite Stream
	    Stream.generate(Math::random)
	      .limit(5)
	      .forEach(System.out::println);
		
		//Method-1
		Stream.of(arrayOfEmps);
		
		//Method-2
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		empList.stream();      
		
		//Method-3
		Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
		
		//Method-4
		Stream.Builder<Employee> empStreamBuilder = Stream.builder();
		empStreamBuilder.accept(arrayOfEmps[0]);
		empStreamBuilder.accept(arrayOfEmps[1]);
		empStreamBuilder.accept(arrayOfEmps[2]);
		Stream<Employee> empStream = empStreamBuilder.build();
		
	    List<List<String>> namesNested = Arrays.asList( 
	    	      Arrays.asList("Jeff", "Bezos"), 
	    	      Arrays.asList("Bill", "Gates"), 
	    	      Arrays.asList("Mark", "Zuckerberg"));
	    List<String> namesFlatStream = namesNested.stream()
	    	      .flatMap(Collection::stream)
	    	      .collect(Collectors.toList());

	    
	    //Convert Iterators to Streams
 	    //StreamSupport, Spliterator
	    System.out.println("======Convert Iterators to Streams=====");
	    List<String> initialList = new ArrayList<String>();
	    initialList.add("One");initialList.add("Two");initialList.add("Three");initialList.add("Four");
	    List<String> newList = StreamSupport.stream(initialList.spliterator(), false).collect(Collectors.toList());
	    System.out.println(newList);

	}
}

class Employee {
	private int id;
	private String name;
	private double salary;
	public Employee(int id, String name, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public void salaryIncrement(double d) {
		this.salary = this.salary + d;
	}
}


//Topics Covered
/*
filter
map
	mapToInt,mapToLong...
flatMap
distinct
sorted..comparator
peek
limit
skip
forEach
forEachOrdered

toArray
toArray
reduce

collect
min
max
count
anyMatch
allMatch
noneMatch

findFirst
findAny

of
empty
iterate
generate
concat
*/

//   https://dzone.com/articles/a-guide-to-streams-in-java-8-in-depth-tutorial-wit
//   J Linker
