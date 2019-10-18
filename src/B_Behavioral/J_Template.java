package B_Behavioral;

abstract class Algorighm {
	public abstract void initilize();
	public abstract void sorting();
	public abstract void printResult();
	
	public void sort() {
		initilize();
		sorting();
		printResult();
	}
}

class BubbleSort extends Algorighm{
	private int[] numbers;
	public BubbleSort(int[] numbers) {
		this.numbers = numbers;
	}
	@Override
	public void initilize() {
		System.out.println("BubbleSort.initilize()");
	}
	@Override
	public void sorting() {
		System.out.println("BubbleSort.sorting()");
	}
	@Override
	public void printResult() {
		for(int i=0;i<numbers.length;i++) {
			System.out.print(numbers[i]+" ");
		}
	}
}


public class J_Template {
	public static void main(String[] args) {
		int[] numbers = {1,2,3,5,-6,-7};
		Algorighm alg = new BubbleSort(numbers);
		alg.sort();
	}
}
