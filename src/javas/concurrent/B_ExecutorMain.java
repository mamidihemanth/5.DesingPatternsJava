package javas.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class B_ExecutorMain {
	
	public static final Executor FIXED_EXEC_1= Executors.newCachedThreadPool();
	public static final ThreadPoolExecutor FIXED_EXEC_2 =  (ThreadPoolExecutor)Executors.newCachedThreadPool();
	public static final ExecutorService FIXED_EXEC_3 =  Executors.newCachedThreadPool();
	public static final ExecutorService FIXED_EXEC_4= Executors.newWorkStealingPool();
	
	public static final ScheduledExecutorService SCHEDULED_EXEC = Executors.newScheduledThreadPool(1);
	
	public static final Executor EXEC = new Executor() {
		@Override
		public void execute(Runnable command) {
			new Thread(command).start();
		}
	};
	
	public static final Runnable RUNNABLE = new Runnable() {
		@Override
		public void run() {
			System.out.println("====STARTED===="+Thread.currentThread().getName());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("====ENDED===="+Thread.currentThread().getName());
		}
	};
	
	public static final Callable<Integer> CALLABLE = new Callable<Integer>() {
		@Override
		public Integer call() throws Exception {
			int TIMER = (int) (Math.random()*10);
			System.out.println("====STARTED===="+Thread.currentThread().getName()+" Timer:"+TIMER);
			TimeUnit.SECONDS.sleep(TIMER);
			System.out.println("====ENDED===="+Thread.currentThread().getName()+" Timer:"+TIMER);
			return TIMER;
		}
		
	};
	
	public static void executorTest() {
		EXEC.execute(RUNNABLE); EXEC.execute(RUNNABLE); EXEC.execute(RUNNABLE);
	}
	public static void executorsTest() {
		FIXED_EXEC_1.execute(RUNNABLE);FIXED_EXEC_1.execute(RUNNABLE);
		
		FIXED_EXEC_3.execute(RUNNABLE);FIXED_EXEC_3.execute(RUNNABLE);
		FIXED_EXEC_3.shutdown();
	}
	public static void threadPoolExecutorTest() {
		FIXED_EXEC_2.execute(RUNNABLE);FIXED_EXEC_2.execute(RUNNABLE);FIXED_EXEC_2.execute(RUNNABLE);
	}
	public static void scheduleFutureTest() {
		ScheduledFuture<?> scheduledFuture = SCHEDULED_EXEC.scheduleAtFixedRate(RUNNABLE, 5, 5, TimeUnit.SECONDS);
	}
	
	public static void executorCompletionServiceTest() throws Exception{
		
		List<Future<Integer>> futuresList = new ArrayList<Future<Integer>>();
		System.out.println("========BLOCKING FUTURE==========");
		futuresList.add(FIXED_EXEC_3.submit(CALLABLE)); futuresList.add(FIXED_EXEC_3.submit(CALLABLE)); futuresList.add(FIXED_EXEC_3.submit(CALLABLE)); futuresList.add(FIXED_EXEC_3.submit(CALLABLE));
		futuresList.add(FIXED_EXEC_3.submit(CALLABLE)); futuresList.add(FIXED_EXEC_3.submit(CALLABLE)); futuresList.add(FIXED_EXEC_3.submit(CALLABLE)); futuresList.add(FIXED_EXEC_3.submit(CALLABLE));
		for(Future<Integer> each: futuresList) {
			System.out.println(each.get());
		}
		
		futuresList = new ArrayList<Future<Integer>>();
		System.out.println("========NON BLOCKING FUTURE==========");
		CompletionService<Integer> executorCompletionService= new ExecutorCompletionService<>(FIXED_EXEC_3);
		futuresList.add(executorCompletionService.submit(CALLABLE)); futuresList.add(executorCompletionService.submit(CALLABLE)); futuresList.add(executorCompletionService.submit(CALLABLE)); futuresList.add(executorCompletionService.submit(CALLABLE));
		futuresList.add(executorCompletionService.submit(CALLABLE)); futuresList.add(executorCompletionService.submit(CALLABLE)); futuresList.add(executorCompletionService.submit(CALLABLE)); futuresList.add(executorCompletionService.submit(CALLABLE));
		for(Future<Integer> each: futuresList) {
			System.out.println(executorCompletionService.take().get());
		}
		FIXED_EXEC_3.shutdown();
	}
	public static void main(String[] args) throws Exception{
		//executorTest();
		//executorsTest();
		//threadPoolExecutorTest();
		//scheduleFutureTest();
		//executorCompletionServiceTest();
		//workStealingPool();
	}
	
	private static void workStealingPool() throws Exception{
		List<Future<Integer>> futuresList = new ArrayList<Future<Integer>>();
		System.out.println("========WORK-STEALING POOL==========");
		futuresList.add(FIXED_EXEC_4.submit(CALLABLE)); futuresList.add(FIXED_EXEC_4.submit(CALLABLE)); futuresList.add(FIXED_EXEC_4.submit(CALLABLE)); futuresList.add(FIXED_EXEC_4.submit(CALLABLE));
		futuresList.add(FIXED_EXEC_4.submit(CALLABLE)); futuresList.add(FIXED_EXEC_4.submit(CALLABLE)); futuresList.add(FIXED_EXEC_4.submit(CALLABLE)); futuresList.add(FIXED_EXEC_4.submit(CALLABLE));
		for(Future<Integer> each: futuresList) {
			System.out.println(each.get());
		}
		FIXED_EXEC_4.shutdown();
	}
}
