package B_Behavioral;

interface MyIterator {
	public boolean hasNext();
	public Object next();
}

class NameIterator implements MyIterator {
	private String[] names; //ArrayList, Set...anything
	private int index;

	public NameIterator(String[] names) {
		this.names = names;
	}

	@Override
	public boolean hasNext() {
		return index < this.names.length;
	}

	@Override
	public Object next() {
		if (hasNext()) {
			return this.names[index++];
		}
		return null;
	}

}

public class D_Iterator {
	public static void main(String[] args) {
		
		String[] names = { "Adam", "Joe", "John", "Sarah" };
		MyIterator Iterator = new NameIterator(names);
		while(Iterator.hasNext()) {
			System.out.println(Iterator.next());
		}
		
	}
}
