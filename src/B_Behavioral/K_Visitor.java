package B_Behavioral;

import java.util.ArrayList;
import java.util.List;


interface ShoppingItem {
	public double visit(ShoppingCartVisitor visitor);
}
interface ShoppingCartVisitor {
	public double visit(Table table);
	public double visit(Chair chair);
}

class ShoppingCart implements ShoppingCartVisitor {

	@Override
	public double visit(Table table) {
		return table.getPrice();
	}

	@Override
	public double visit(Chair chair) {
		return chair.getPrice();
	}

}
class Table implements ShoppingItem {

	private double price;
	private String name;

	public Table(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double visit(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}
}

class Chair implements ShoppingItem {

	private double price;
	private String name;

	public Chair(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public double visit(ShoppingCartVisitor visitor) {
		return visitor.visit(this);
	}
}


public class K_Visitor {
	public static void main(String[] args) {
		List<ShoppingItem> items = new ArrayList<>();
		items.add(new Table("table",22));
		items.add(new Table("table",3));
		items.add(new Table("table",2));
		items.add(new Chair("chair",45));
		double sum = 0;
		ShoppingCartVisitor shoppingCart = new ShoppingCart();
		
		for(ShoppingItem shoppingItem : items) {
			sum = sum + shoppingItem.visit(shoppingCart);
		}
		System.out.println(sum);
	}
}
