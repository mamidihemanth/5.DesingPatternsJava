package C_Structural;

interface Shape {
	void draw();

	void resize();

	String description();

	boolean isHide();
}

class Circle implements Shape {
	@Override
	public void draw() {
		System.out.println("Drawing Circle");

	}

	@Override

	public void resize() {

		System.out.println("Resizing Circle");

	}

	@Override

	public String description() {

		return "Circle object";

	}

	@Override

	public boolean isHide() {

		return false;

	}

}


class Rectangle implements Shape {

    @Override

    public void draw() {

    System.out.println("Drawing Rectangle");

    }

    @Override

    public void resize() {

    System.out.println("Resizing Rectangle");

    }

    @Override

    public String description() {

    return "Rectangle object";

    }

    @Override

    public boolean isHide() {

    return false;

    }

}

public class D_Decarator {

}
