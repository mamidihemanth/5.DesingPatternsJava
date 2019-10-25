Important Java Packages:
========================
1. [java.util.concurrent](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent)
2. [java.util.concurrent.atomic](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent/atomic)
3. [java.util.concurrent.locks](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent/lock)
4. [java.util.stream](https://github.com/siripuramjagadishraj1/5.DesingPatternsJava8/tree/master/src/javas/concurrent/streams)
5. java.util.function
6. java.time


JVM DEEP DIVE:
==============
* Java VMS example: [HotSportVirtual, Eclipse, Open J9]
* HotSportVirtual Implementations [Oracle JDK, Open JDK]
* JVM Languages: [Scala, Kotlin, Groovy, Java...]


* ByteCode
* JIT Compiler, Complation is done by separate thread.
* C1[1,2,3] and C2[4] Complation Levels.
* Profiling the Code: JVM know which level of complation to use, this is called as profiling.<br>
`$>java -XX:+PrintCompilation Main 10` [n=native methods, s=synchronized, %=code cached, !=>Exception Handling.]
	
* In remote JVMS:
>java -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation Main 5000 // prduces a Log file. to observe the output

* Tuning code cache.
>java -XX:+PrintCodeCache Main 50 //Shows code cache
Java-7: 32 or 48 MB
Java-8: 240MB
	InitialCodeCacheSize
	ReservedCodeCacheSize
	CodeCacheExpansionSize
Eg: 
>java -XX:ReservedCodeCacheSize=28k -XX:+PrintCodeCache Main 5

* JConsole: code cache observation.
* 32 vs 64 bit JVM and when to choose what.


>java -XX:-TieredCompilation -XX:+PrintCompilation Main 4000		//Tired compilation
>java -client -XX:-TieredCompilation -XX:+PrintCompilation Main 400 //client mode[client, server, d64]
>java -XX:+PrintFlagsFinal											//All commands.

jinfo -flag CICompilerCount 5908
java -XX:CICompilerCount=6 -XX:+PrintCompilation Main 500 -XX:CompileThreshold=1000
java -XX:CICompilerCount=6 -XX:+PrintCompilation -XX:PermSize=100 Main 5	//warning in Java-8
java -XX:CICompilerCount=6 -XX:-PrintCompilation -XX:MaxPermSize=100 Main   //warning in Java-8





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
