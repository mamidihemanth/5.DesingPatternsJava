package javas.concurrent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class C_DataStructures {
	public static final BlockingQueue<Integer> BLOCK_QUEUE = new ArrayBlockingQueue<Integer>(4);
	public static final BlockingDeque<Integer> BLOCK_D_QUEUE = new LinkedBlockingDeque<Integer>(4);
	public static final Runnable Q_RUNNER = ()->{
		for(int i=0;i<10;i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(BLOCK_QUEUE.poll()+" ThreadName: "+Thread.currentThread().getName());
		}
	};
	public static final Runnable DQ_RUNNER = ()->{
		for(int i=0;i<10;i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if( Math.random() > 0.555)
				System.out.println(BLOCK_D_QUEUE.pollLast()+" ThreadName: "+Thread.currentThread().getName());
			else
				System.out.println(BLOCK_D_QUEUE.pop()+" ThreadName: "+Thread.currentThread().getName());
		}	
	};
	
	public static final SynchronousQueue<Integer> SYNCHROOUS_QUEUE = new SynchronousQueue<Integer>();
	
	public static void blockingQueue() throws Exception{
		new Thread(Q_RUNNER).start();
		for(int i=0;i<10;i++)
			BLOCK_QUEUE.put(i);
		System.out.println("========PROGRAM ENDS========"+Thread.currentThread().getName());
	}
	
	public static void blockingDeQueue() throws Exception{
		new Thread(DQ_RUNNER).start();
		for(int i=0;i<10;i++)
			BLOCK_D_QUEUE.put(i);
		System.out.println("========PROGRAM ENDS========"+Thread.currentThread().getName());
	}
	
	public static void concurrentMap() {
		Map<Integer, String> concurrentMap = new ConcurrentHashMap<Integer, String>();
			concurrentMap.put(1, "One");
			concurrentMap.put(2, "Two");
			concurrentMap.put(3, "Three");
			concurrentMap.put(4, "Four");
			System.out.println(concurrentMap);
		concurrentMap = new ConcurrentSkipListMap<Integer, String>();
			concurrentMap.put(1, "One");
			concurrentMap.put(2, "Two");
			concurrentMap.put(3, "Three");
			concurrentMap.put(4, "Four");
			System.out.println(concurrentMap);
	}
	
	public static void copyOnWriteDS() {
		CopyOnWriteArraySet<Integer> copy = new CopyOnWriteArraySet<Integer>();
		copy.add(1);
		copy.add(1);
		copy.add(3);
		System.out.println(copy);
	}
	
	public static void main(String[] args) throws Exception{
		//blockingQueue();
		//sychronousQueue();
		//blockingDeQueue();	//Double ended Queue.
		//blockingDelayDueue();
		//blockingPriorityQueue();
		//transferQueue();   //??
		//synchronousQueueTest();
		//threadLocalRandom();
		//semaphoreTest();
		//concurrentMap();
		//copyOnWriteDS();
		//exchangerTest();
		//countDownLatch_cyclicBarrier();
		
	}

	private static final CyclicBarrier BARRIER = new CyclicBarrier(3);
	public static final Runnable CYCLIC_BARRIER_RUN= ()->{
		try {
			System.out.println(Thread.currentThread().getName()+" Before Sleeping..");
			TimeUnit.SECONDS.sleep(2);
			System.out.println(Thread.currentThread().getName()+" After Sleeping..");
			BARRIER.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	private static final CountDownLatch LATCH = new CountDownLatch(3);
	public static final Runnable LATCH_RUN= ()->{
		try {
			System.out.println(Thread.currentThread().getName()+" Before Sleeping..");
			TimeUnit.SECONDS.sleep(2);
			System.out.println(Thread.currentThread().getName()+" After Sleeping..");
			LATCH.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	private static void countDownLatch_cyclicBarrier() throws Exception{
		/*
		LATCH.countDown();
		LATCH.countDown();
		LATCH.countDown();
		LATCH.await();
		System.out.println("All Latches have completed..");
		*/
		
		new Thread(LATCH_RUN).start();
		new Thread(LATCH_RUN).start();
		new Thread(LATCH_RUN).start();
		LATCH.await();
		System.out.println("All Latches have completed..");
		
		new Thread(CYCLIC_BARRIER_RUN).start();
		new Thread(CYCLIC_BARRIER_RUN).start();
		new Thread(CYCLIC_BARRIER_RUN).start();
		BARRIER.await();
		BARRIER.reset();
		System.out.println("All Barriers have completed..");
	}

	public static final Exchanger<String> EXCHANGE = new Exchanger<String>();
	public static final Runnable EXCH_RUNNER_1 = ()->{
		try {
			System.out.println("Inside: "+Thread.currentThread().getName()+" HERE");
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+" - "+EXCHANGE.exchange("PUSH"));
			Thread.sleep(3000);
			System.out.println(" Exchanged ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	public static final Runnable EXCH_RUNNER_2 = ()->{
		try {
			System.out.println("Inside: "+Thread.currentThread().getName()+"");
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+" - "+EXCHANGE.exchange("PULL"));
			Thread.sleep(3000);
			System.out.println(" Exchanged ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	private static void exchangerTest() {
		new Thread(EXCH_RUNNER_1).start();
		new Thread(EXCH_RUNNER_2).start();
	}

	private static void sychronousQueue() throws Exception{
		BlockingQueue<Integer> syncQueue = new SynchronousQueue<>(true); //fair = true > the waiting threads will be granted access in FIFO (First-In First-Out) order.
		Runnable r = ()->{
			int count =0;
			while(true) {
				try {
					System.out.println(Thread.currentThread().getName()+ "Started");
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+" "+syncQueue.take());
					if(count++ == 10)
						break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(r).start();
		new Thread(r).start();
		new Thread(r).start();
		
		for(int i=0;i<100;i++)
			syncQueue.put(i);
		
	}

	private static void semaphoreTest() {
		Runnable r1 = ()->{
			LibrarySemaphoreRunner runner= new LibrarySemaphoreRunner();
			try {
				System.out.println(Thread.currentThread().getName() +" Trying to Acquiring ");
				runner.semS.acquire();
				System.out.println(Thread.currentThread().getName() +" Acquiring ");
				Thread.sleep(2000);
				runner.semS.release();
				System.out.println(Thread.currentThread().getName() +" Releasing ");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r1).start();
		new Thread(r1).start();
		
	}

	private static void threadLocalRandom() {
		//more efficient in a highly concurrent environment
		int unboundedRandomValue = ThreadLocalRandom.current().nextInt(0,10);
		System.out.println(unboundedRandomValue);
	}

	private static void synchronousQueueTest() throws Exception{
		new Thread(()->{
			while(true) {
				try {
					Thread.sleep(1000);
					System.out.println(SYNCHROOUS_QUEUE.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		for(int i=0;i<10;i++) {
			System.out.println("============First Item");
			SYNCHROOUS_QUEUE.put(i);
			System.out.println("============Next Item");
		}
	}

	private static void transferQueue() throws Exception{
		TransferQueue<Integer> tq = new LinkedTransferQueue<Integer>();
		for(int i=0;i<10;i++) {
			System.out.println("C_DataStructures.transferQueue()");
			tq.put(i);
		}
		
		while(true) {
			Thread.sleep(1000);
			System.out.println(tq.poll());;
		}
	}

	private static void blockingPriorityQueue() throws Exception{
		PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<Integer>(); 
		for(int i=0;i<10;i++)
			pbq.add((int)(Math.random()*100));
		
		System.out.println(pbq);
		Thread.sleep(500);
		while(true) {
			Thread.sleep(1000);
			System.out.println(pbq.poll());;
		}
	}

	private static void blockingDelayDueue() throws Exception{
		DelayQueue<Student> delay = new DelayQueue<Student>();
		for(int i=0;i<10;i++)
			delay.add(new Student());
		
		while(true) {
			Thread.sleep(1000);
			System.out.println(delay.poll());;
		}
	}
	
	
}

class Student implements Delayed{
	private static int count =0;
	
	private int time = (int) (Math.random()*10 - 7 );
	
	public Student() {
		System.out.println("Student.Student()"+count++ +" : "+this);
	}

	@Override
	public int compareTo(Delayed o) {
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		System.out.println("Student.getDelay()"+time);
		return time;
	}
	
	@Override
	public String toString() {
		return this.time+":";
	}
}

class LibrarySemaphoreRunner{
	public static final Semaphore sem = new Semaphore(2);
	Semaphore semS=sem;
	public LibrarySemaphoreRunner(){}
	String booksAll[]= {"JAVA","ORACLE"};
	List<String> books = Arrays.asList(booksAll);
	public List<String>  getBooks(){
		return books;
	}
}