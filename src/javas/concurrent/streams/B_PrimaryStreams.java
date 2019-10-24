package javas.concurrent.streams;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class B_PrimaryStreams {
	public static void main(String[] args) {
		IntStream.range(1, 4).forEach(System.out::println);
		System.out.println("=============================");
		Arrays.stream(new int[] {1,2})
				.map(n -> n*n)
				.average()
				.ifPresent(System.out::println);
		System.out.println("=============================");
		Stream.of(1.6,2.3)
			.mapToInt(Double::intValue)
			.forEach(System.out::print);
		System.out.println("\n=============================");
	}
}
