import java.util.ArrayList;
import java.util.List;

public class CodeCacheTest {
	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<Integer>();
		for(int i=0;i<new Integer(args[0]);i++) {
			if(isOdd(i))
			arrayList.add(i);
		}
		System.out.println(arrayList);
	}
	public static boolean isOdd(int number) {
		if(number%2==0)
			return true;
		else
			return false;
	}
}
