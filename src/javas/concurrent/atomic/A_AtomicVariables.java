package javas.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class A_AtomicVariables {
	public static void main(String[] args) throws Exception{
		//testAtomicVariable();
		atomicIntegerArray();
	}

	public static final AtomicIntegerArray array = new AtomicIntegerArray(20);
	private static void atomicIntegerArray() {
		for(int i=0;i<20;i++)
			array.addAndGet(i, i+1);
		System.out.println(array);
	}

	private static final AtomicInteger integer = new AtomicInteger(0);
	private static final Runnable RUNNER_N= ()->{
		for(int i=0;i<1000;i++)
			integer.getAndAdd(1);
	};
	private static void testAtomicVariable() throws Exception{
		new Thread(RUNNER_N).start();
		new Thread(RUNNER_N).start();
		new Thread(RUNNER_N).start();
		new Thread(RUNNER_N).start();
		new Thread(RUNNER_N).start();
		Thread.sleep(3000);
		System.out.println(integer);
	}
}
