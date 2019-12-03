package Ex1;

import java.io.IOException;

import com.sun.corba.se.impl.copyobject.CopierManagerImpl;

public class main {

	public static void main(String[] args) throws Exception {
	

		Monom m = new Monom (2, 1);
		Monom e = new Monom (4, 0);
		Monom n = new Monom (3, 2);
		ComplexFunction cf1 = new ComplexFunction("times", m, e);
		System.out.println(cf1);
		ComplexFunction cf2 = new ComplexFunction("plus", cf1, n );
		System.out.println(cf2);
		String s = cf2.toString();
		
//		cf2 = new ComplexFunction("times", cf2, cf2);
//		System.out.println(cf2);
		ComplexFunction x = new ComplexFunction("plus", m, e);
//		String s = cf2.toString();
		x = (ComplexFunction) x.initFromString(s);
		System.out.println(x);
	

		
		
		
	}

}
