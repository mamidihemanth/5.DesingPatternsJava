package A_creational;

//1. Not Thread Safe.
class SingleTonA{
	private static SingleTonA singleTonA;
	private SingleTonA() {}
	public static SingleTonA getSingleTon() {
		if(singleTonA == null) {
			singleTonA = new SingleTonA();
		}
		return singleTonA;
	}
}

//2. Thread Safe but blocking
class SingleTonB{
	private static SingleTonB singleTonB;
	private SingleTonB() {}
	public synchronized static SingleTonB getSingleTon() {
		if(singleTonB == null) {
			singleTonB = new SingleTonB();
		}
		return singleTonB;
	}
}

//3. Thread Safe unblocking but performance problem.[Double Checking..]
class SingleTonC{
	private static SingleTonC singleTonC;
	private SingleTonC() {}
	public static SingleTonC getSingleTon() {
		if(singleTonC == null) {
			//If 2 threads reach here.
			synchronized(SingleTonC.class) {
				//ReCheck..
				if(singleTonC == null) {
					singleTonC = new SingleTonC();
				}
			}
		}
		return singleTonC;
	}
}

//4. BillPugh SingleTon: best
class BillPugh{
	private BillPugh() {}
	private static class BillPughSingleton{ 
	    private static final BillPugh INSTANCE = new BillPugh(); 
	}
	public static BillPugh getSingleTon() { 
	    return BillPughSingleton.INSTANCE; 
	} 
}
//NOTE: 1-4 are all Lazy Loading, 5,6 are Eager Loading.

//5. Using Enums eager Loading
enum EnumSingleTon {
	SINGLE_TON;
	private EnumSingleTon() {}
}
//6. Similar to Enums
class EnumSingleTonClass {
	private EnumSingleTonClass() {}
	public final static EnumSingleTonClass SINGLE_TON = new EnumSingleTonClass();
}


public class A_SingleTon {
	public static void main(String[] args) throws Exception{
		System.err.println("NOTE: 1-4 are all Lazy Loading, 5 & 6 are Eager Loading.");
		System.out.println("1. Non Thread safe                                                   :"+ (SingleTonA.getSingleTon() == SingleTonA.getSingleTon()) );
		System.out.println("2. Thread Safe but blocking                                          :"+(SingleTonB.getSingleTon() == SingleTonB.getSingleTon()));
		System.out.println("3. Thread Safe unblocking but performance problem.[Double Checking..]:"+(SingleTonC.getSingleTon() == SingleTonC.getSingleTon()));
		System.out.println("4. BillPugh SingleTon: [best]                                        :"+(BillPugh.getSingleTon() == BillPugh.getSingleTon()));
		System.out.println("======================================================================");
		System.out.println("5. Using Enums                                                       :"+(EnumSingleTon.SINGLE_TON == EnumSingleTon.SINGLE_TON));
		System.out.println("6. Similar to Enums                                                  :"+(EnumSingleTonClass.SINGLE_TON == EnumSingleTonClass.SINGLE_TON));
	}
}

