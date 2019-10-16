package A_creational;

interface Shape{
	 public void getArea();
}

class Triangle implements Shape{
	@Override
	public void getArea() {
		System.out.println("Triangle.getArea()");
	}
}

class Square implements Shape{
	@Override
	public void getArea() {
		System.out.println("Square.getArea()");
	}
}

class ShapeFactoryImpl{
	public static Shape getShape(String type) {
		if("SQUARE".equals(type))
			return new Square();
		else
			return new Triangle();
	}
}


public class C_FactoryMethod {
	public static void main(String[] args) {
		System.err.println("Return Objects of a particular Family");
		ShapeFactoryImpl.getShape("SQUARE").getArea();
		ShapeFactoryImpl.getShape("TRIANGLE").getArea();
	}
}
