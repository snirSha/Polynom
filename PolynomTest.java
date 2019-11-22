package Ex1;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
	}
	public static void test1() {
		System.out.println("** TEST 1 **");
		try {
			Polynom p1 = new Polynom();
			String[] monoms = {"1","x","x^2", "0.5x^2"};
			for(int i=0;i<monoms.length;i++) {
				Monom m = new Monom(monoms[i]);
				p1.add(m);
			}
			System.out.println(p1.toString());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void test2() {
		System.out.println("\n** TEST 2 **");
		try {
			Polynom p1 = new Polynom(), p2 =  new Polynom();
			String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
			String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
			//		String[] monoms1 = {"x^2"};
			//		String[] monoms2 = {"3x^4"};
			for(int i=0;i<monoms1.length;i++) {
				Monom m = new Monom(monoms1[i]);
				p1.add(m);
			}
			for(int i=0;i<monoms2.length;i++) {
				Monom m = new Monom(monoms2[i]);
				p2.add(m);
			}
			System.out.println("p1: "+p1);
			System.out.println("p2: "+p2);
			p1.add(p2);
			System.out.println("p1+p2: "+p1);
			p1.multiply(p2);
			System.out.println("(p1+p2)*p2: "+p1);
			String s1 = p1.toString();
			Polynom_able pp1 = new Polynom(s1);
			System.out.println("from string: "+pp1);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void test3() {
		System.out.println("\n** TEST 3 **");
		try {
			Polynom p1 = new Polynom();
			System.out.println("empty Polynom(): " + p1.toString());
			p1 = new Polynom(" +3x -8x^2 +x^5+7-x^3");
			System.out.println("Polynom(String s): x^5 -x^3 +3X -8x^2 +7 , got " + p1.toString());
			p1 = new Polynom("-2x^2 + x^3");
			System.out.println("f(2) = 0, " + p1.f(2));
			p1 = new Polynom("3x^2");
			p1.add(new Monom(2, 5));
			System.out.println("add func: 2x^5 + 3x^2: " + p1.toString());
			System.out.println("isZero: " + p1.isZero() + ":* should be false *");
			p1 = new Polynom("x");
			System.out.println("root: " +  p1.root(-190, 190, 0.1));
			System.out.println("area: " + (Math.abs(p1.area(0, 2, 0.000001) - 2) < 0.000001));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void test4() {
		System.out.println("\n****  Test 4  ****");
		try {
			String s1="-8x^5 + 7x^4 -x",s2="56x^12+45x^32-x^7",s3="15x^1+23x+12x",s4="10x^2-45x^3+20x^2+15x^3",
					s5="45x^32-2x^7+x^7",s6="-31x^3+29x^2+49x",s7="x+1";

			Polynom p1=new Polynom(s1);
			Polynom p2=new Polynom(s2);
			Polynom p3=new Polynom(s3);
			Polynom p4=new Polynom(s4);
			Polynom p5=new Polynom(s5);
			Polynom p6=new Polynom(s6);
			Polynom p7=new Polynom(s7);

			System.out.println("p1: " +s1+" = " + p1.toString());
			System.out.println("p2: " +s2+" = " + p2.toString());
			System.out.println("p3: " +s3+" = " + p3.toString());
			System.out.println("p4: " +s4+" = " + p4.toString());
			System.out.println("p5: " +s5+" = " + p5.toString());
			System.out.println("p6: " +s6+" = " + p6.toString());
			System.out.println("p7: " +s7+" = " + p7.toString());


			System.out.println("\n****  check add,substruct and multiply  ****");

			p1.add(p2);
			p3.add(p4);
			System.out.println("p1+p2= "+ p1+ "\np3+p4= "+ p3);
			p1.substract(p5);
			p3.substract(p6);
			System.out.println("(p1+p2)-p5= "+ p1);
			System.out.println("(p3+p4)-p6= "+ p3);

			Monom m=new Monom(2,1);
			p3.multiply(m);
			System.out.println("[(p3+p4)-p6]*2x= "+ p3);
			p3.multiply(p7);
			System.out.println("[(p3+p4)-p6]*2x*p7= "+p3);

			System.out.println("\n****  check f(2),equals,isZero,derivative  ****");
			System.out.println("f(x=2) = 2x^5+4x^4+4x^3+2x^2 = "+p3.f(2));
			Polynom tmp=new Polynom("x+1");
			System.out.println("p7==x+1 ? " +p7.equals(tmp));
			System.out.println("p7.isZero ? "+ p7.isZero());
			System.out.println("[2x^5+4x^4+4x^3+2x^2] derivative = "+p3.derivative());


			System.out.println("\n****  check root,area,copy  ****");
			double ans=p3.root(-1, 1, 0.000001);
			System.out.println("[10.0x^4 + 16.0x^3 + 12.0x^2 + 4.0x].root(-1,1,0.000001) = "+ ans);
			System.out.println("copy of x+1 =" +p7.copy());
			System.out.println("(x+1).area(0,1,0.000001) = " +p7.area(0, 1, 0.000001));
			System.out.println("(x+1).area(1,0,0.000001) = " +p7.area(1, 0, 0.000001));


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void test5() {
		System.out.println("\n****  Test5 (Catch Errors)  ****");
		try {
			String [] badPoly= {"3x5^7+1","--x^4+3x","(x+2)*(x+1)","x^2-45c^2",""}; 
			for (int i = 0; i < badPoly.length; i++) {
				Polynom pol=new Polynom(badPoly[i]);
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}