package Ex1;

import java.awt.Color;
import java.util.ArrayList;


public class main {

	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};

	public static void main(String[] args) {


		Monom m = new Monom (2, 1);
		Monom e = new Monom (4, 0);
		Monom n = new Monom (3, 2);
		//		ComplexFunction cf1 = new ComplexFunction("times", m, e);
		//		System.out.println(cf1);
		//		ComplexFunction cf2 = new ComplexFunction("plus", cf1, n );
		//		System.out.println(cf2);
		//		String s = cf2.toString();
		//
		//		ComplexFunction x = new ComplexFunction("plus", m, e);
		//
		//		x = (ComplexFunction) x.initFromString(s);
		//		System.out.println(x);
		//		x = new ComplexFunction("plus", x, x);
		//		System.out.println(x);
		//		String s1 = x.toString();
		//		function x1 = x.initFromString(s1);

		ComplexFunction cf = new ComplexFunction("times", m, n);
		Functions_GUI fg = new Functions_GUI();
		fg.add(cf);
		fg.add(m);
		fg.add(new Polynom("3x^4 - x^3 - 2x^2 - 5"));
		fg.add(new Polynom("-2x^3 + 5x^2 - 1.5x - 7"));	
		fg.add(new Monom(1, 1));
		fg.add(new Monom (1, 2));
		fg.add(new Polynom ("x^4 - 4x^3 + 10"));

		ArrayList<function> ff = new ArrayList<function>();
		ff.addAll(fg);
		Range x = new Range(-15, 15);
		Range y = new Range(-15, 15);
		fg.drawFunctions(800, 600, x, y, 500);



	}
}
