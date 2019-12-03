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
		fg.add(new Polynom("3x^4 - x^3 - 2x^2 + 4"));
		fg.add(new Polynom("-2x^3 + 5x^2 - 1.5x - 7"));		

		ArrayList<function> ff = new ArrayList<function>();
		ff.addAll(fg);
		drawFunctions(ff);



	}



	public static void drawFunctions(ArrayList<function> ff){
		drawFunctions(ff, GUI_Params.DEFAULT_GUI_PARAMS);
	}
	public static void drawFunctions(ArrayList<function> ff, GUI_Params gp) {
		drawFunctions(ff, gp.get_width(),gp.get_height(),gp.get_rx(), gp.get_ry(),gp.get_resolution());
	}
	public static void drawFunctions(ArrayList<function> ff, int width, int height, Range rx, Range ry, int res) {
		int n = res;
		StdDraw.setCanvasSize(width, height);
		int size = ff.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {
				yy[a][i] = ff.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());




		for(int a=0;a<size;a++) {
			StdDraw.setPenColor(Color.DARK_GRAY);

			StdDraw.line(0, -600, 0, 600);
			StdDraw.line(-800, 0, 800, 0);
		}	


		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);

			System.out.println(a+") "+Colors[a]+"  f(x)= "+ff.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}	
	}

	/**
	 * Simple example for inner class.
	 */
	static class GUI_Params {
		public static final int W=800, H=600, RES=1000;
		public static final Range RX=new Range(-10,10);
		public static final Range RY=new Range(-10,15);
		public static GUI_Params  DEFAULT_GUI_PARAMS = new GUI_Params(W,H,RX,RY,RES);
		private int _width;
		private int _height;
		private Range _rx;
		private Range _ry;
		private int _resolution;
		public GUI_Params(int w, int h, Range rx, Range ry, int res) {
			this._width = w;
			this._height = h;
			this._rx = rx;
			this._ry = ry;
			this._resolution = res;
		}
		public int get_width() {return this._width;}
		public int get_height() {return this._height;}
		public int get_resolution() {return this._resolution;}
		public Range get_rx() {return this._rx;}
		public Range get_ry() {return this._ry;}
	}

}
