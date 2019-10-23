package javas.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class A_LockCondition {
	private final static Lock aLock = new ReentrantLock();
	public static final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	public static final Runnable RUNN = ()->{
		System.out.println("Inside"+Thread.currentThread().getName());
		aLock.lock();
		try {
			TimeUnit.SECONDS.sleep(3);
			System.out.println("TaskDOne"+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Unlocking"+Thread.currentThread().getName());
			aLock.unlock();
		}
	};
	
	public static void rentrantLock() {
		new Thread(RUNN).start();
		new Thread(RUNN).start();
		new Thread(RUNN).start();
	}
	public static void main(String[] args) {
		//rentrantLock();
		readWriteLock();
	}
	private static void readWriteLock() {
		Lock read =  rwLock.readLock();
		Lock write = rwLock.writeLock();
		read.unlock();
		write.unlock();
	}
}
