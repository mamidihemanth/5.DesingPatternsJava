Important Commands:
-------------------
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal

java -XX:+PrintCommandLineFlags -version

java -client -XX:+PrintCompilation Main 5000

Chapter-1, 2, 3:
----------------
* Java VMS example: [HotSportVirtual, Eclipse, Open J9]
* HotSportVirtual Implementations [Oracle JDK, Open JDK]
* JVM Languages: [Scala, Kotlin, Groovy, Java...]

* ByteCode
* JIT Compiler, Compilation is done by separate thread.
* C1[1,2,3] and C2[4] Compilation Levels.
* Profiling the Code: JVM know which level of compilation to use, this is called as profiling.<br>
>java -XX:+PrintCompilation Main 10  [n=native methods, s=synchronized, %=code cached, !=>Exception Handling.]
	
* In remote JVMS:
>java -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation Main 5000 // produces a Log file. to observe the output

* Tuning code cache.
>java -XX:+PrintCodeCache Main 50 //Shows code cache

Java-7: 32 or 48 MB

Java-8: 240MB, [InitialCodeCacheSize, ReservedCodeCacheSize, CodeCacheExpansionSize]
>java -XX:ReservedCodeCacheSize=28k -XX:+PrintCodeCache Main 5

* JConsole: code cache observation.
* 32 vs 64 bit JVM and when to choose what.


>java -XX:-TieredCompilation -XX:+PrintCompilation Main 4000		 //Tired compilation

>java -client -XX:-TieredCompilation -XX:+PrintCompilation Main 400      //client mode[client, server, d64]

>java -XX:+PrintFlagsFinal						 //All commands.

>jinfo -flag CICompilerCount 5908

>java -XX:CICompilerCount=6 -XX:+PrintCompilation Main 500 -XX:CompileThreshold=1000

>java -XX:CICompilerCount=6 -XX:+PrintCompilation -XX:PermSize=100 Main 5	//warning in Java-8

>java -XX:CICompilerCount=6 -XX:-PrintCompilation -XX:MaxPermSize=100 Main      //warning in Java-8

JVM which one to use.
-----------------------
* if heap<3GB -> 32 bit JVM is better (Pointer = 32 bit)
* if heap>4GB -> 64 bit JVM is better
* Max heap size of 64 bit JVM determined by OS, windows=1.2GB
* 32 bit vm uses client copiler(c1)
* 64 bit vm uses client+server compiler(c1+c2)
Therefore, on server always use 64 bit JVM.

client flag:
-------------
>java -client -XX:+PrintCompilation Main 5000 //-client, -server, -d64 modes available
* -d64 sometimes gives error in 32 bit os.

Chapter-4,5,6,7,8,9:
--------------------
* Java is always pass by value.
* MetaSpace from java-8: classes[Bytecode & NativeCode], Methods, static variables are stored and never garbage collected.
* in Java-7 there was permgen space.
* Java can sometimes create objects in stack too, we cant tell.
java-6: strings were stored in permgen space, and it had limited space.
java-7: strings are stored in heap space and can be garbage collected.
java-8: perm-gen space is replaced by metaspace.

Chapter-10:
-----------
* String DeDuplication (in Future)
* String-pool is implemented using hashmap and its size should be optimized.
>java -XX:+PrintStringTableStatistics
>java -XX:+PrintStringTableStatistics Main 100  //shows no of buckets..etc
* String Table size can also be changed.
>java -XX:+PrintStringTableStatistics -XX:StringTableSize=120121 Main 100  //No of buckets can be changed.
* Default memory size=4GB, 2^32 bytes.
Therefore, don't use maxHeapSize to change heap size, Rather set InitialHeapSize to improve speed of application.

>java -XX:MaxHeapSize=600m -XX:+PrintStringTableStatistics -XX:StringTableSize=120121 Main 100
Default Memory Size=4GB => 2^32 bytes.

Therefore, don't use MaxHeapSize to change heap size, rather set initial heap size to improve speed of application

>java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal 

>java -XX:MaxHeapSize=1G -XX:InitialHeapSize=1G Main 100

Chapter-11:
-----------
* Java 11 GC can give back unused memory back to OS. This is due to optimizaitons in GC.

Chapter:12, 13:[Tools]
---------------
* Soft Leak/Memory Leak: Objects referenced from stack but no longer needed.
* JVisualVM tool for Java-8, GRAL-Visual VM for Java-9.
* MAT: Memory Analyzer took & analyzing heap dump.

Chapter-14:
----------
* Mark and Sweep Alogrithm.
* Generatoins in GarbageCollecions. (Genearational GC) Young/Old Generation
Principles of GC:
	1. Objects don't live longer.
	2. If Objects live its likely to live longer.
* No of Generatoins for GC is configurable. [Visual GC Plugin.]

Chapter-15:
-----------
* SoftLeak vs Memory Leak..?
* Video 65,66 important code to observe GC in action.

>jinfo -flag NewRatio 10692      //Old = 2*Young

>jinfo -flag SurvivorRatio 10692 //so, s1 = 1/8 Young Generation

>jinfo -flag MaxTenuringThreshold //15= Max, 16 implies only Young generation.

Chapter-16:
-----------
>java -XX:+PrintCommandLineFlags -version  //Tells you the default GC being used.

Java Default GC's vs versions.
1. Serial 		      -XX:+UseSerialGC			(Older Java)
2. Parallel		      -XX:userParallelGC		(java-8)
3. Mostly Concurrent
	1. -XX:+UseConcMarkSweepGC				(java-9)
	2. -XX:+UseG1GC						(Introduced-6, Java-10)

* String de-duplication.
 -XX:+UseStringDeduplication //Use this only in combination with G1 algorithm.(Garbage First)
>java -XX:+PrintCommandLineFlags -XX:+UseG1GC -XX:+UseStringDeduplication Main 10
* JMC: Java Mission Control. [Open Source.]
* Using a profiler. Eg:- JMC, Jprofiler, UR-Kit
git clone .....

Chapter-17, 18, 19: Java Benchmarking
-------------------------------------

Chapter-20, 21:
---------------
* How List works.
* 8 types of list.
	1. LinkedList
	2. ArrayList
	3. CopyOnWriteArrayList
	4. Stack
	5. Vector
	-------
	6. AttributeList			M-Bean  Managed Bean.
	7. RoleList
	8. RoleUnresolvedList
* How Map Works.
LoadFacotr=0.75, InitialSize, Factor.

Chapter-22:
-----------
* Loops vs Streams

Chapter-23:
-----------
GraalVM- Linux open-JDK-11 contains GraalVM and written in java.
Features:
 1. Alternatives to JVM and Faster.
 2. Alternative to Java Compiler much better.
 3. Native Compiler (No JVM Required).
Graal Compiler produces a JavaNative class file like .exe.
* Run the example using java-11 with experimental features enabled.
> java -XX:-UnLockExperimentalVMOptins
       -XX:+EnableJVMCI
       -XX:+UseJVMCICompiler Main 100

Chapter-24:
-----------
* Other JVM Choices [Kotlin, Groovy... etc.]
* Bytecode in javap
> javap -v Main.class
* javap is a diassembler not a decompiler.

Chapter-25:
-----------
* Eclipse Open J9 is much better than other VMS.
* Eclipse Open J9 is HotSpot VM.
* Beware of Logging.

