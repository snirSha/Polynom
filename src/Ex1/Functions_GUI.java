package Ex1;


import java.awt.Color;
import com.google.gson.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
/**
 * 
 * @author Omer Kalif & Snir Sharabani
 *
 */
public class Functions_GUI implements functions{
	/**
	 * Colors is the array of colors that we use to draw each function in other color
	 */
	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};
	/**
	 * @list is the collection of the functions
	 */
	public ArrayList<function> list = new ArrayList<function>();
	/**
	 * add function to the list
	 */
	public boolean add(function e) {
		try {
			return this.list.add(e);
		}catch(Exception err) {
			System.out.println("could not add non function type");
			return false;
		}	
	}
	/**
	 * add collection of functions to the list
	 */
	public boolean addAll(Collection<? extends function> c) {
		return this.list.addAll(c);
	}
	/**
	 * delete all the functions from the list
	 */
	public void clear() {
		this.list.clear();
	}
	/**
	 * check if an object is in the list
	 */
	public boolean contains(Object o) {
		return this.list.contains(o);
	}
	/**
	 * check if a collection is contained in the list
	 */
	public boolean containsAll(Collection<?> c) {
		return this.list.containsAll(c);
	}
	/**
	 * check if the list is empty
	 */
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
	/**
	 * return an iterator of the type function
	 */
	public Iterator<function> iterator() {
		return this.list.iterator();
	}
	/**
	 * remove an object from the list
	 */
	public boolean remove(Object o) {
		return this.list.remove(o);
	}
	/**
	 * remove a collection from the list
	 */
	public boolean removeAll(Collection<?> c) {
		return this.list.removeAll(c);
	}
	/**
	 * retain the list with a collection
	 */
	public boolean retainAll(Collection<?> c) {
		return this.list.retainAll(c);
	}
	/**
	 * return the size of the collection
	 */
	public int size() {
		return this.list.size();
	}
	/**
	 * return an array of the functions in the list
	 */
	public Object[] toArray() {
		return this.list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return this.list.toArray(a);
	}
	
	public String toString() {
		String ans = "";
		for (int i = 0; i < size(); i++) {
			ans += this.list.get(i).toString();
			if(i + 1 < size()) ans += "\n";
		}
		return ans;
	}
	/**
	 * read a text file of functions and initialize a collection of those functions
	 */
	public void initFromFile(String file) throws IOException {
		try {
			String s = FileUtils.readFile(file);
			String [] str = s.split("\n");
			for (int i = 0; i < str.length; i++) {
				function cf2 = new ComplexFunction();
				cf2 = cf2.initFromString(str[i]);
				add(cf2);
			}
		}catch(Exception e) {
			throw new IOException("Can not read file"); 
		}
	}
	/**
	 * save the collection in a text file
	 */
	public void saveToFile(String file) throws IOException {
		FileUtils.writeFile(file, toString());
	}
	/**
	 * draw the functions in a java window by given parameters
	 */
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
				try {
				yy[a][i] = this.list.get(a).f(x[i]);
				}catch(Exception e) {
					continue;
				}
			}
			x0+=x_step;
		}
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());

		// draw x & y lines
		StdDraw.setPenColor(new Color (225, 225, 225));//Draw gray lines
		for (int i = (int)rx.get_min() * 2; i <= rx.get_max() * 2; i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		for (int i = (int)ry.get_min() * 2; i <= ry.get_max() * 2; i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		StdDraw.setPenColor(Color.BLACK);//Draw the main lines in black
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
		for(int a = 0; a < size; a++) {
			int c = a % Colors.length;
			StdDraw.setPenColor(Colors[c]);
			System.out.println(a + ") " + Colors[a % Colors.length] + "  f(x)= " + this.list.get(a));
			for (int i = 0; i < n; i++) {
				StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
			}
		}
	}
	/**
	 * draw all the lists functions by parameters from the json file
	 */
	public void drawFunctions(String json_file) {
		try {
			String s = FileUtils.readFile(json_file);
			Gson gson = new Gson();
			try {
				GUI_params gp = gson.fromJson(s, GUI_params.class);
				Range rx = new Range(gp.Range_X[0], gp.Range_X[1]);
				Range ry = new Range(gp.Range_Y[0], gp.Range_Y[1]);
				if(gp.Resolution < 1 || gp.Range_X[0] >= gp.Range_X[1] 
						|| gp.Range_Y[0] >= gp.Range_Y[1] 
								|| gp.Width <= 0 || gp.Height <= 0) {
					throw new IOException(); 
				}
				drawFunctions(gp.Width, gp.Height, rx, ry, gp.Resolution);
			}catch(Exception e){ 
				System.out.println("File is not readable or include wrong values, init with default values.");
				Range rx = new Range(-15, 15);
				Range ry = new Range(-15, 15);
				drawFunctions(800, 600, rx, ry, 500);
			}
		} catch (IOException e) {
			System.out.println("File is not readable or include wrong values, init with default values.");
			Range rx = new Range(-15, 15);
			Range ry = new Range(-15, 15);
			drawFunctions(800, 600, rx, ry, 500);
		}
	}
	/**
	 * 
	 * class for the json file
	 *
	 */
	public class GUI_params { 
		int Width;
		int Height;
		int Resolution;
		double [] Range_X;
		double [] Range_Y;

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Width: " + Width + "\nHeight: " + Height + "\nResolution: " 
					+ Resolution + "\nRange_X: " + Arrays.toString(Range_X)
					+ "\nRange_Y: " + Arrays.toString(Range_Y));
			return sb.toString();
		}
	}
}
