package B_Behavioral;

interface Strategy {
	public void operation(int num1, int num2);
}

class Multiply implements Strategy {

	@Override
	public void operation(int num1, int num2) {
		System.out.println(num1 * num2);
	}
}

class Add implements Strategy {

	@Override
	public void operation(int num1, int num2) {
		System.out.println(num1 + num2);
	}
}

class OperatorManager{
	private Strategy strategy;
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	public void executeOperation(int num1, int num2) {
		this.strategy.operation(num1, num2);
	}
}

public class I_Strategy {
	public static void main(String[] args) {
		OperatorManager manager = new OperatorManager();
		manager.setStrategy(new Multiply());
		manager.executeOperation(5, 5);
		manager.setStrategy(new Add());
		manager.executeOperation(5, 5);

	}
}
