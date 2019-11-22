package Ex1;
import java.util.ArrayList;

/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
 *****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {

		test1();
		test2();
		ourTest();

	}

	private static void test1() {
		System.out.println("*****  Test1:  *****");
		try {
			String[] monoms = {"2", "-x","-3.2x^2","0"};
			for(int i=0;i<monoms.length;i++) {
				Monom m;
				m = new Monom(monoms[i]);
				String s = m.toString();
				m = new Monom(s);
				double fx = m.f(i);
				System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		try {
			ArrayList<Monom> monoms = new ArrayList<Monom>();
			monoms.add(new Monom(0,5));
			monoms.add(new Monom(-1,0));
			monoms.add(new Monom(-1.3,1));
			monoms.add(new Monom(-2.2,2));

			for(int i=0;i<monoms.size();i++) {
				Monom m = new Monom(monoms.get(i));
				String s = m.toString();
				Monom m1 = new Monom(s);
				boolean e = m.equals(m1);
				System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	private static void ourTest() {
		System.out.println("*****  Our Tests:  *****");
		try {
			System.out.println("\n****  check Monom(String s) and toString  ****");
			String[] goodMonoms = {"2x^6","8x^3","4x","156","-1x^123"};
			for (int i = 0; i < goodMonoms.length; i++) {
				Monom m=new Monom(goodMonoms[i]);
				String s=m.toString();
				System.out.println(s);
			}

			System.out.println("\n****  check derivative  ****");
			for(int i=0; i<goodMonoms.length;i++) {
				Monom m=new Monom(goodMonoms[i]);
				String s=m.derivative().toString();
				System.out.println(s);
			}

			System.out.println("\n****  check f(2)  *****");
			for(int i=0; i<goodMonoms.length;i++) {
				double x=2;	
				Monom m=new Monom(goodMonoms[i]);		
				System.out.println(m.f(x));
			}
			System.out.println("\n****  check add  *****");
			Monom m0=new Monom(goodMonoms[0]);
			Monom m1=new Monom(3,6);
			Monom m2=new Monom(goodMonoms[2]);
			Monom m3=new Monom(8.5,1);
			Monom m4=new Monom(goodMonoms[4]);
			Monom m5=new Monom(-564,123);

			m0.add(m1);//5x^6
			System.out.println("2x^6 + 3x^6 = " + m0.toString());
			m2.add(m3);//12.5x
			System.out.println("4x + 8.5x = " + m2.toString());
			m4.add(m5);//-565x^123
			System.out.println("-x^123 + -564x^123 = " + m4.toString());		

			System.out.println("\n****  check substruct  ****");
			m0=new Monom(goodMonoms[1]);
			m1=new Monom(8,3);
			m2=new Monom(goodMonoms[3]);
			m3=new Monom(-98,0);
			m4=new Monom(goodMonoms[4]);
			m5=new Monom(64,123);
			m0.substract(m1);
			m2.substract(m3);
			m4.substract(m5);
			System.out.println("8x^3 - 8x^3 = " + m0);
			System.out.println("156 - (-98) = " + m2);
			System.out.println("-x^123 - 64^123 = "+ m4);

			System.out.println("\n****  check multiply  ****");
			m0=new Monom(goodMonoms[0]);
			m1=new Monom(goodMonoms[1]);
			m2=new Monom(goodMonoms[2]);
			m3=new Monom(goodMonoms[3]);
			m4=new Monom(goodMonoms[4]);
			m0.multipy(m1);
			m2.multipy(m3);
			m1.multipy(m4);
			System.out.println("2x^6 * 8x^3 = " + m0);
			System.out.println("4x * 156 = " + m2);
			System.out.println("8x^3 * -x^123 = " + m1);

			System.out.println("\n****  check equals  *****");
			System.out.println("16x^9==16x^9 ? " + m0.equals(new Monom(16,9)));
			System.out.println("-8^126==-8x ? " + m1.equals(new Monom(-8,1)));
			System.out.println("624x==624 ? "+ m2.equals(new Monom(624,0)));
			System.out.println("156==156 ? " + m3.equals(new Monom(156,0)));
			System.out.println("-x^123==-x^123 ? " + m4.equals(new Monom(-1,123)));

			System.out.println("\n****  check Errors  ****");
			String[] badMonoms = {"","gfkjguo","45*/x^6","2^7","(2)","^"};
			for (int i = 0; i < badMonoms.length; i++) {
				Monom m=new Monom(badMonoms[i]);
				String s=m.toString();
				System.out.println(s);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
