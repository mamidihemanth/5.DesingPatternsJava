package javas.concurrent.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A_StreamTest {
	/*
	map, reduce, 
	Stream Operations:
		1. Intermediate: sorted
		2. Terminal: collect
	
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
		// sorted
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
		System.out.println("findFirst : " + nameList.stream().findFirst().get());
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
