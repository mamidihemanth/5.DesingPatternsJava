package javas.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;




public class CompletableFutures {

	private static int TIMER = (int) (Math.random()*10000)/2;
	public static final Runnable RUNNABLE_WITH_DELAY = () -> {
		try {
			System.out.println("Started.."+TIMER);
			Thread.sleep(TIMER);
			System.out.println("Runnable..");
			Thread.sleep(TIMER);
			System.out.println("Runnable..Completed..!"+Thread.currentThread().getThreadGroup()+" :"+TIMER);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	public static final Callable<Integer> CALLABLE_WITH_DELAY = () -> {
		int TIMERS = (int) (Math.random()*10000)/2;
		System.out.println("Started.."+TIMERS);
		Thread.sleep(TIMERS);
		System.out.println("Callable..");
		Thread.sleep(TIMERS);
		System.out.println("Callable..Completed..!"+TIMERS);
		//if(1==1) throw new RuntimeException();
		return 1;
	};
	
	public static final Supplier<Integer> SUP= ()->{ 
		int sleepTime = (int) (Math.random()*10000);
		System.out.println("Sleeping in SUPPLIER :"+sleepTime);
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("OVER in SUPPLIER :"+sleepTime);
		return (int) (sleepTime);
	};
	
	public static final Function<Integer, Integer> FUN = (a)->{ 
		System.out.println("Sleeping in FUNCTION -> Parameter:" +a);
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a;
	};
	
	public static final Consumer<Integer> CON= (item)->{
		System.out.println("Sleeping in CONSUMER -> Parameter:" +item);
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	
	public void usingRunnable() throws InterruptedException{
		//Cons:
		//1. Can't throw Exceptoin's or return value.
		//2. Its Output value is unPredictable.
		System.out.println("1****STARTED usingRunnable");
		Thread t = new Thread(RUNNABLE_WITH_DELAY);
		t.start();
		t.join();
		System.out.println("1****ENDED usingRunnable");
	}
	
	public void usingThreadGroup() throws Exception{
		//Using ThreadGroup
		System.out.println("2****STARTED usingThreadGroup");
		ThreadGroup group1 = new ThreadGroup("GROUP1");
		Thread t1= new Thread(group1, RUNNABLE_WITH_DELAY);t1.start();
		Thread t2= new Thread(group1, RUNNABLE_WITH_DELAY);t2.start();
		System.out.println(group1.activeCount());
		System.out.println(group1.activeGroupCount());
		t1.join();t2.join();
		System.out.println("2****ENDED usingThreadGroup");
	}
	
	public void usingExecutor() throws InterruptedException{
		System.out.println("4****STARTED usingExecutor");
		Executor exec = new Executor() {
			@Override
			public void execute(Runnable runn) {
				Thread t =new Thread(runn);
				t.start();
			}
		};
		exec.execute(RUNNABLE_WITH_DELAY);
		exec.execute(RUNNABLE_WITH_DELAY);
		exec.execute(RUNNABLE_WITH_DELAY);
		System.out.println("4****ENDED usingExecutor");
	}
	
	public void usingCallable() throws Exception{
		System.out.println("3****STARTED usingCallable");
		ExecutorService es= Executors.newCachedThreadPool();
		Future<Integer> one= es.submit(CALLABLE_WITH_DELAY);
		Future<Integer> two= es.submit(CALLABLE_WITH_DELAY);
		System.out.println(one.get()+" - "+two.get()+" Got The future Object..!");
		System.out.println("3****ENDED usingCallable");
		es.shutdown();
	}
	
	public void usingSimpleCompletable() throws Exception{
		CompletableFuture<Integer> comp = CompletableFuture.completedFuture(43);
		System.out.println(comp.get());
		
		comp = CompletableFuture.supplyAsync(()->{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 5;
			});
		System.out.println("Still waiting...!");
		System.out.println(comp.get());
	}
	
	public static void main(String[] args) throws Exception {
		CompletableFutures ts = new CompletableFutures();
		//ts.usingRunnable();
		//ts.usingThreadGroup();
		//ts.usingCallable();
		//ts.usingExecutor();
		//ts.usingSimpleCompletable();
		usingCompletable();
		//futuresWithTimeOut();
	}

	private static void futuresWithTimeOut() throws Exception{
		//1. Future with TimeOut
		System.out.println("***************Futrues with timeOut");
		ExecutorService es = Executors.newCachedThreadPool();
		Future<Integer> future = es.submit(CALLABLE_WITH_DELAY);
		Object obj = future.get(10,TimeUnit.SECONDS);
		System.out.println(obj+"\n===========================");
		
		//1. List of FutureList with invokeAll
		System.out.println("***************List of Callable & InvokeAll");
		ExecutorService executor = Executors.newWorkStealingPool();
		List<Callable<Integer>> callables = Arrays.asList(CALLABLE_WITH_DELAY,CALLABLE_WITH_DELAY);
		List<Future<Integer>> futureList = executor.invokeAll(callables);
		for(Future<Integer> each: futureList) {
			System.out.println(each.get());
		}
		System.out.println("***************List of Callable via Streams");
		//2. FutureList alternative way	
		executor.invokeAll(callables)
		.stream().map( fut-> { 
			try {
				return fut.get();
			} catch (Exception e) {
				throw new IllegalAccessError();
			}
		}).forEach(System.out::println);
		
		System.out.println("***************Schedule Service Executor");
		ScheduledExecutorService sexecutor = Executors.newScheduledThreadPool(1);
		ScheduledFuture<?> futureA = sexecutor.schedule(CALLABLE_WITH_DELAY, 3, TimeUnit.SECONDS);
		System.out.println(System.nanoTime());
		long remainingDelay = futureA.getDelay(TimeUnit.MILLISECONDS);
		System.out.println(remainingDelay);
		
		sexecutor.scheduleAtFixedRate(RUNNABLE_WITH_DELAY, 0, 1, TimeUnit.SECONDS);
	}

	private static void usingCompletable() throws Exception{
		System.out.println("****************thenApply");
		CompletableFuture<Integer> java = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Integer> completing = java.thenApply(FUN);
		System.out.println(completing.get());
		
		System.out.println("****************thenAccept");
		CompletableFuture<Integer> java2 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Void> completing2 = java2.thenAccept(CON);
		System.out.println(completing2.get());
		
		System.out.println("****************applyToEither");
		CompletableFuture<Integer> java3 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Integer> scala3 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Integer> both  = java3.applyToEither(scala3, title -> title);
		System.out.println(both.get());
		Thread.sleep(10000);
		
		System.out.println("****************allOf");
		CompletableFuture<Integer> java4 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Integer> java5 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Void> allCompleted = CompletableFuture.allOf(java4, java5);
		System.out.println(allCompleted.get());
		
		System.out.println("****************anyOf");
		CompletableFuture<Integer> java6 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Integer> java7 = CompletableFuture.supplyAsync(SUP);
		CompletableFuture<Object> anyCompleted = CompletableFuture.anyOf(java6, java7);
		System.out.println(anyCompleted.get());
	}
	
	public Future<String> calculateAsync() throws InterruptedException {
	    CompletableFuture<String> completableFuture = new CompletableFuture<>();
	 
	    Executors.newCachedThreadPool().submit(() -> {
	        Thread.sleep(500);
	        completableFuture.complete("Hello");
	        return null;
	    });
	 
	    return completableFuture;
	}
	

}


/*
 * Feedback.epam.com PMS
 * JAN-JUNE
 * JULY-DEC
 * 28-OCT to Nov-30- WriteStage
 * 					Summarize & Communicate
 * 					Communicate
 * 
 * selfreview,
 * 
 * 
 * mywall, give feedback
 * 
 */