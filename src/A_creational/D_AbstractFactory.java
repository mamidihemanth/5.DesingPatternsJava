package A_creational;

interface GlobasFactory{
	public ShapeDimension getShape(String type);
}

interface ShapeDimension{
	public void getSurfaceArea();
}

interface Shape_2D extends ShapeDimension{
}

class Triangle_2d implements Shape_2D{
	@Override
	public void getSurfaceArea() {
		System.out.println("Triangle_2d.getSurfaceArea()");
	}
}

class Square_2d implements Shape_2D{
	@Override
	public void getSurfaceArea() {
		System.out.println("Square_2d.getSurfaceArea()");
	}
}

class ShapeFactory_2D_Impl implements GlobasFactory{
	public Shape_2D getShape(String type) {
		if("SQUARE".equals(type))
			return new Square_2d();
		else
			return new Triangle_2d();
	}
}


interface Shape_3D extends ShapeDimension{
	 public void getSurfaceArea();
}

class Triangle_3D implements Shape_3D{
	@Override
	public void getSurfaceArea() {
		System.out.println("Triangle_3D.getSurfaceArea()");
	}
}

class Square_3D implements Shape_3D{
	@Override
	public void getSurfaceArea() {
		System.out.println("Square_3D.getSurfaceArea()");
	}
}

class ShapeFactory_3D_Impl implements GlobasFactory{
	public ShapeDimension getShape(String type) {
		if("SQUARE".equals(type))
			return new Square_3D();
		else
			return new Triangle_3D();
	}
}

class AbstractFactoryImpl{
	public static GlobasFactory getShapeFactory(String type) {
		if("2D".equals(type))
			return new ShapeFactory_2D_Impl();
		else
			return new ShapeFactory_3D_Impl();
	}
}
public class D_AbstractFactory {
	public static void main(String[] args) {
		GlobasFactory shapes_2d = AbstractFactoryImpl.getShapeFactory("2D");
		GlobasFactory shapes_3d = AbstractFactoryImpl.getShapeFactory("3D");
		
		shapes_2d.getShape("SQUARE").getSurfaceArea();
		shapes_2d.getShape("RECTANGE").getSurfaceArea();
		shapes_3d.getShape("SQUARE").getSurfaceArea();
		shapes_3d.getShape("RECTANGE").getSurfaceArea();
	}
}
