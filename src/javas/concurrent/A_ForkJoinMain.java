package javas.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

//NOTE: CompletableFuture, ParallellStream use commonPool
class ForkJoinExample extends RecursiveAction{ //Use RecuriveTask when you want to return something
	public List<Integer> interList = new ArrayList<Integer>();
	
	public ForkJoinExample(List<Integer> interList){
		this.interList = interList;
	}
	
	@Override
	public void compute(){
		if(interList.size()==1){
			System.out.println(interList.get(0)+","+Thread.currentThread().getName()+" ThreadId:");
		}else{
			ForkJoinExample leftPart= new ForkJoinExample(interList.subList(0, (int)interList.size()/2));
			ForkJoinExample rightPart= new ForkJoinExample(interList.subList((int)interList.size()/2,(int)interList.size()));
			
			System.out.println(leftPart.interList+" : "+ rightPart.interList);
			leftPart.fork();
			rightPart.compute();
			leftPart.join();
			System.out.println("========================");
		}
	}
}

public class A_ForkJoinMain {
	public static void main(String[] args) {
		
		System.out.println("CPU Core: " + Runtime.getRuntime().availableProcessors());
		System.out.println("CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());
		System.out.println("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism());
		
		List<Integer> interList = new ArrayList<Integer>(); //fill with 10 elm
		for(int i=1;i<=10;i++) interList.add(i);
		
		
		System.out.println("========================");
		ForkJoinExample example = new ForkJoinExample(interList);
		example.compute();
		System.out.println("========ALL DONE========");
	}
}
