import java.util.Date;

public class Main {

	public static void main(String[] args) throws Exception{
		PrimeNumbers primeNumbers = new PrimeNumbers();
		Integer max = Integer.parseInt(args[0]);
		primeNumbers.generateNumbers(max);
	}

}
