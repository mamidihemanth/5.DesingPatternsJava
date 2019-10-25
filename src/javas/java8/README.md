Important Java Packages:
========================
1. [java.util.concurrent](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent)
2. [java.util.concurrent.atomic](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent/atomic)
3. [java.util.concurrent.locks](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent/lock)
4. [java.util.stream](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent/streams)
5. java.util.function
6. java.time


JAVA-8 Design Principles:
=========================
1. Do not Return Null to Indicate the Absence of a Value
2. Do not Use Arrays to Pass Values to and From the API 
3. Consider Adding Static Interface Methods to Provide a Single Entry Point for Object Creation
4. Favor Composition With Functional Interfaces and Lambdas Over Inheritence
5. Add the @FunctionalInterface Annotation to Functional Interfaces
6. Avoid Overloading Methods With Functional Interfaces as Parameters
7. Avoid Overusing Default Methods in Interfaces
8. Ensure That the API Methods Check the Parameter Invariants Before They Are Acted Upon
9. Do not Simply Call Optional.get()
10. Consider Separating Your Stream Pipeline on Distinct Lines in Implementing API Classes




JAVA-Concurrency:
=================
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

JAVA DOCS


