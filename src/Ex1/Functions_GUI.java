package Ex1;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



public class Functions_GUI implements functions{
	
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};

	public ArrayList<function> list = new ArrayList<function>();

	@Override
	public boolean add(function e) {
		if(contains(e))return false;
		return this.list.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {
		return this.list.addAll(c);
	}

	@Override
	public void clear() {
		this.list.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return this.list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.list.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		return this.list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return this.list.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.list.retainAll(c);
	}

	@Override
	public int size() {
		return this.list.size();
	}

	@Override
	public Object[] toArray() {
		return this.list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.list.toArray(a);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.size(); i++) {
			sb.append(this.list.get(i).toString());
			if(i < size() - 1) sb.append(", ");
		}
		return sb.toString();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int res) {
		int n = res;
		StdDraw.setCanvasSize(width, height);
		int size = this.size();
		double[] x = new double[n+1];
		double[][] yy = new double[size][n+1];
		double x_step = (rx.get_max()-rx.get_min())/n;
		double x0 = rx.get_min();
		for (int i=0; i<=n; i++) {
			x[i] = x0;
			for(int a=0;a<size;a++) {
				yy[a][i] = this.list.get(a).f(x[i]);
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		
		// draw x & y lines
		for (int i = (int)rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		for (int i = (int)ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.setPenColor(Color.LIGHT_GRAY);
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		StdDraw.setPenColor(Color.BLACK);
		for (int i = (int)rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, -.1, i, .1);
			String s = "";
			s += i;
			if(i != 0)StdDraw.text((double)i, -.6, s);	
		}
		for (int i = (int)ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(-.1, i, .1, i);
			String s = "";
			s += i;
			if(i != 0)StdDraw.text(-.5, (double)i, s);
		}
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());

		// plot the approximation to the function
		for(int a=0;a<size;a++) {
			int c = a%Colors.length;
			StdDraw.setPenColor(Colors[c]);
			System.out.println(a+") "+Colors[a]+"  f(x)= "+this.list.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}	
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

	
}
