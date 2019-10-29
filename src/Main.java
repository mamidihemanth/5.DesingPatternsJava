import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception{
		TimeUnit.SECONDS.sleep(60);
		PrimeNumbers primeNumbers = new PrimeNumbers();
		Integer max = Integer.parseInt(args[0]);
		primeNumbers.generateNumbers(max);
	}

}
