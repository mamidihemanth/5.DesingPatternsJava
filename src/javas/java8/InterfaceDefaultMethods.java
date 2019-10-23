package javas.java8;

interface Left{
	default void display(){
		System.out.println("Left.enclosing_method()");
	}
}

interface Right{
	default void display(){
		System.out.println("Left.enclosing_method()");
	}
}

public class InterfaceDefaultMethods implements Left, Right{

	@Override
	public void display() {
		Left.super.display();
	}

}
