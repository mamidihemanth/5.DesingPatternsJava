package B_Behavioral;


interface MyFilter {
	public abstract void doFilter();
}

class FirstFilter implements MyFilter {
	private MyFilter filter;
	public void setFilter(MyFilter filter) { this.filter = filter; }
	@Override
	public void doFilter() {
		System.out.println("FirstFilter Before Filter");
		filter.doFilter();
		System.out.println("FirstFilter After Filter");
	}
}

class SecondFilter implements MyFilter{
	private MyFilter filter;
	public void setFilter(MyFilter filter) {this.filter = filter;}
	@Override
	public void doFilter() {
		System.out.println("	SecondFilter.java Before Filter");
		filter.doFilter();
		System.out.println("	SecondFilter.java After Filter");
	}
}

class ThirdFilter implements MyFilter{
	private MyFilter filter;
	public void setFilter(MyFilter filter) {this.filter = filter;}
	@Override
	public void doFilter() {
		System.out.println("		ThirdFilter.java Before Filter>\n");
		if(filter != null)
			filter.doFilter();
		System.out.println("		ThirdFilter.java After Filter>");
	}
}

public class A_ChainOfResponsibity {
	public static void main(String[] args) {
		FirstFilter f1 = new FirstFilter();
		SecondFilter f2 = new SecondFilter();
		ThirdFilter f3 = new ThirdFilter();
			f1.setFilter(f2);
			f2.setFilter(f3);
		f1.doFilter();
	}
}
