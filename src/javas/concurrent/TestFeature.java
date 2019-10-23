package javas.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
ForkJoinPool.commonPool()

CompletionStage: Its an interface for CompletableFuture
StampedLock:     ?
LongAdder: 		LongAdder vs AtomicLong
LongAccumulator
DoubleAdder
DoubleAccumulator
CountedCompleter:	?
Executors.newWorkStealingPool()
Executors.newWorkStealingPool(int)

ConstructorReferences
InstanceMethodReferences
IntStream
Reduce
 */

public class TestFeature {
	public static final Consumer<String> CONS = (t)->{
		try {
			Thread.sleep(500);
			System.out.println(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	};
	
	public static void main(String[] args) {
		List<String> eachList = new ArrayList<String>();
		eachList.add("1");
		eachList.add("1");
		eachList.add("3");
		eachList.add("4");
		eachList.add("5");
		
		//eachList.parallelStream().forEach(CONS);
		System.out.println("=================================");
		//eachList.stream().sequential().forEach(CONS);
		System.out.println("=================================");
		List<Integer> eachListInteger = eachList.stream().filter(e-> e.length() > 0).map(s -> new Integer(s)).collect(Collectors.toList());
		//System.out.println(eachListInteger);
		 //eachList.stream().filter(e-> e.length() > 0).map(s -> new Integer(s)).forEach( e-> { System.out.println(e instanceof Integer);});
		 eachList.stream().filter(e-> e.length() > 0).skip(3).forEach(e->System.out.println(e));
		 System.out.println("=================================");
		 eachList.removeIf(e->e=="1");
		 System.out.println(eachList);
		//Intermediate : FIlter, Map
		//Terminal     : 
		 
		 //FlatMap
		 LongAccumulator accumulator = new LongAccumulator(Long::sum, 0L);
		 LongAdder counter = new LongAdder();
		 ExecutorService executorService = Executors.newFixedThreadPool(8);
		 
		 int numberOfThreads = 4;
		 int numberOfIncrements = 100;
		 
		  
		 Runnable accumulateAction = () -> IntStream
		   .rangeClosed(0, numberOfIncrements)
		   .forEach(accumulator::accumulate);
		  
		 for (int i = 0; i < numberOfThreads; i++) {
		     executorService.execute(accumulateAction);
		 }
		
	}
}
