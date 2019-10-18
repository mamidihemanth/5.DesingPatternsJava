package C_Structural;

interface Algorithm {
	public void sort();
}

class BubbleSort implements Algorithm {
	@Override
	public void sort() {
		System.out.println("Bubbesort...");
	}
}

class HeapSort implements Algorithm {
	@Override
	public void sort() {
		System.out.println("Heapsort...");
	}
}

class MergeSort implements Algorithm {
	@Override
	public void sort() {
		System.out.println("Mergesort...");
	}
}

class SortingManager {

	private Algorithm bubbleSort;
	private Algorithm mergeSort;
	private Algorithm heapSort;

	public SortingManager() {
		this.bubbleSort = new BubbleSort();
		this.mergeSort = new MergeSort();
		this.heapSort = new HeapSort();
	}

	public void mergeSort() {
		this.mergeSort.sort();
	}

	public void bubbleSort() {
		this.bubbleSort.sort();
	}

	public void heapSort() {
		this.heapSort.sort();
	}
}

public class E_Facade {
	public static void main(String[] args) {
		SortingManager manager = new SortingManager();
		manager.bubbleSort();
		manager.mergeSort();
		manager.heapSort();

	}
}
