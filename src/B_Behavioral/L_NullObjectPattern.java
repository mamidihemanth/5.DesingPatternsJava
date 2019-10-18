package B_Behavioral;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractCustomer {
	protected String personName;
	public abstract boolean isNull();
	public abstract String getCustomer();
	
}

class NullCustomer extends AbstractCustomer{
	@Override
	public boolean isNull() {
		return true;
	}
	@Override
	public String getCustomer() {
		return "No customer with given name in Database..";
	}
}

class RealCustomer extends AbstractCustomer{
	private String customerName;
	public RealCustomer(String customerName) {
		this.customerName = customerName;
	}
	@Override
	public boolean isNull() {
		return false;
	}
	@Override
	public String getCustomer() {
		return this.customerName;
	}
}
class Database {
	private List<String> customerNames;
	public Database() {
		this.customerNames = new ArrayList<String>();
		this.customerNames.add("One");
		this.customerNames.add("Two");
		this.customerNames.add("John");
		this.customerNames.add("Four");
	}
	public boolean existCustomer(String name) {
		for(String s:customerNames) {
			if(s.equals(name))
				return true;
			
		}
		return false;
	}
}

class CustomerFactory {
	private Database databse;
	public CustomerFactory() {
		this.databse = new Database();
	}
	public AbstractCustomer getCustomer(String name) {
		if(checkName(name)) {
			return new RealCustomer(name);
		}
		return new NullCustomer();
	}
	private boolean checkName(String name) {
		if(databse.existCustomer(name)) {
			return true;
		}
		return false;
	}
	
	
}

public class L_NullObjectPattern {
	public static void main(String[] args) {
		 CustomerFactory cf = new CustomerFactory();
		 System.out.println(cf.getCustomer("Threes").getCustomer());
		 System.out.println(cf.getCustomer("John").getCustomer());
	}
}
