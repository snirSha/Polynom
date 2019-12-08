package Ex1;

import java.awt.Color;
import java.io.IOException;
import com.google.gson.Gson;

import Ex1.Functions_GUI.GUI_params;


public class main {



	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, 
			Color.red, Color.GREEN, Color.PINK};

	public static void main(String[] args) throws IOException {


		Monom m = new Monom (2, 1);
		Monom e = new Monom (4, 0);
		Monom n = new Monom (3, 2);
		Polynom p = new Polynom("3x^2 - 5x + 4");
		Polynom p1 = new Polynom("x^3 - x^2 + x -1");

		ComplexFunction [] arr = new ComplexFunction[7];
		arr[0] = new ComplexFunction("times", m, n);//Times(2x,3x^2)
		arr[1] = new ComplexFunction("divid", arr[0], p);//Divid(Times(2x,3x^2),3x^2 - 5x + 4)
		arr[2] = new ComplexFunction(m);//None(2x,NULL)
		arr[3] = new ComplexFunction(p);//None(3x^2 - 5x + 4,NULL)
		arr[4] = new ComplexFunction("plus", p1, n);//Plus(x^3 - x^2 + x -1,3x^2)
		arr[5] = new ComplexFunction(new Polynom("4"));//None(4,NULL)
		arr[6] = new ComplexFunction("divid", arr[4], arr[0]);//Divid(Plus(x^3 - x^2 + x -1,3x^2) , Times(2x,3x^2))
		Functions_GUI fg = new Functions_GUI();

		for (int i = 0; i < arr.length; i++) {
			fg.add(arr[i]);
		}	
	

		fg.saveToFile("func2.txt");
		Functions_GUI fg1 = new Functions_GUI();
		fg1.initFromFile("func2.txt");
		fg1.drawFunctions("params.txt");


	}
		


}
