package Ex1Testing;
/**
 * 

 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	public static final double EPS = 0.00001;
	@Test
	void test() {
		Monom m1 = new Monom(2,2);//2.0x^2
		Monom m2 = new Monom(3,3);//3.0x^3
		ComplexFunction cf = new ComplexFunction("plus", m1, m2);//Plus(3x^3 , 2x^2)
		//System.out.println(cf);
		cf.mul(m2);//Times(Plus(3x^3 , 2x^2),3.0x^3)
		Polynom p = new Polynom();
		p.add(m1);//2.0x^2
		p.add(m2);//3.0x^3
		
		p.multiply(m2);//(2x^2 + 3x^3)*3.0x^3

		double v = 4.0;
		double dp = p.f(v);//43008
		double dcf = cf.f(v);//43008
		double dd = Math.abs(dp-dcf);
		if(dd>EPS) {
			//System.out.println(p+" at "+v+" = "+dp);
			//System.out.println(cf+" at "+v+" = "+dcf);
			fail("ERR: should got the same value from: "+p+"  and "+cf);
		
		}
	}

		@Test
		void testOfString() {
			Polynom p1 = new Polynom();
			p1.add( new Monom(2,2));//2x^2
			Polynom p2 = new Polynom();
			p2.add(new Monom(3,3));//3x^3
			Monom m1 = new Monom(2,2);//2x^2
			Monom m2 = new Monom(3,3);//3x^3
			ComplexFunction cf = new ComplexFunction("plus", m1,m2);//Plus(2.0x^2 , 3.0x^3)
			ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);//Plus(2.0x^2 , 3.0x^3)
//			System.out.println(cf);
			cf.mul(m2);//Times(Plus(2.0x^2 , 3.0x^3) , 3.0x^3)
			cf3.mul(m2);//Times(Plus(2.0x^2 , 3.0x^3) , 3.0x^3)
		    String s = cf.toString();
			function cf2 = cf.initFromString(s);
			if(!cf.equals(cf2)) {
				fail("ERR: "+cf+" should be equals to "+cf2);
			}
			if(!cf.equals(cf3)) {
				fail("ERR: "+cf+" should be equals to "+cf3);
			}
	}
		
	@Test
	void testComplexFunction() throws Exception {
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x -1","x -2", "x -3", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);//x-1
		for(int i=1;i<s3.length;i++) {
			p3.multiply(new Polynom(s3[i]));//(x-1)*(x-2)*(x-3)*(x-4)
		}
		ComplexFunction cf = new ComplexFunction("plus", p1,p2);//(3.1 +2.4x^2 -x^4)+(5 +2x -3.3x +0.1x^5)
		ComplexFunction cf4 = new ComplexFunction("div", new Monom("x"),p3);// x / (x-1)*(x-2)*(x-3)*(x-4)
		cf.div(p1);// [(3.1 +2.4x^2 -x^4)+(5 +2x -3.3x +0.1x^5)] / (3.1 +2.4x^2 -x^4) = 8.1+-1.3x+2.4^2
		String s = cf.toString();
		function cf5 = cf4.initFromString(s);
		if(!cf.equals(cf5)) {
			fail("ERR: "+cf+" should be equals to "+cf5);
		}
		int size=10;
		for(int i=0;i<size;i++) {
			double x = Math.random();
			double d = cf.f(x);
			double d5 = cf5.f(x);
			assertEquals(d,d5,EPS);
		}
		//System.out.println(cf4);
		//System.out.println(cf5);
	}
	
	@Test
	void ourTest1() throws Exception{
		System.out.println("\nOur complicated equals test:");
		String s1="x^2";
		String s2="5";
		Polynom p1=new Polynom(s1);
		Polynom p2=new Polynom(s2);
		ComplexFunction t1= new ComplexFunction("plus",p1,p2);
		ComplexFunction t2=new ComplexFunction("div",t1,p2);
		ComplexFunction t3=new ComplexFunction("mul",t2,p2);
		ComplexFunction t4=new ComplexFunction("div",p2,t3);

		System.out.println(t4);
		String y=t4.toString();
		function gg=t4.initFromString(y);
	
		System.out.println(gg+" == "+t4);
		if(!t4.equals(gg)) {
			fail("ERR: "+t4+" should be equals to "+gg);
		}
	}
	
	@Test
	void ourTest2() throws Exception{
		//System.out.println("\nOur f(x) test:");
		String [] polynoms= {"x","x^2","6","3x^2"};
		Polynom r1=new Polynom(polynoms[0]);
		Polynom r2=new Polynom(polynoms[0]);
		Polynom r3=new Polynom(polynoms[0]);
		for (int i = 1; i < polynoms.length; i++) {
			r1.add(new Polynom(polynoms[i]));
			r2.multiply(new Polynom(polynoms[i]));
			r3.substract(new Polynom(polynoms[i]));
		}
		//System.out.println(r3);
		double x1=r1.f(1);
		double x2=r2.f(1);
		double x3=r3.f(1);
		assertEquals(x1,11.0);
		assertEquals(x2,18.0);
		assertEquals(x3,-9.0);
		}
	
	@Test 
	void ourTest3() throws Exception{
	//	System.out.println("\nOur all little functions: ");
		Monom m1=new Monom("x");
		Monom m2=new Monom("45x");
		Monom m3=new Monom(2,2);
		Polynom p2=new Polynom("5x^5 + 2x");
		Polynom p1=new Polynom("x^2+1");
		ComplexFunction cf=new ComplexFunction("plus",m1,p1);
		cf.div(m2);
		cf.max(m3);
		cf.min(p2);//Min(Max(Divid(Plus(x , x^2 + 1.0) , 45.0x) , 2.0x^2) , 5.0x^5 + 2.0x)
		double x=cf.f(1);
		assertEquals(x,2.0);
	}
	
	@Test
	void ourTest4() throws Exception{
		//System.out.println("\ncopy check");
		String [] st= {"10x^10","9x^9","8x^8","7x^7","6x^6","5x^5","4x^4","3x^3","2x^2","x","0"};
		Polynom p1=new Polynom(st[0]);
		Polynom p2=new Polynom(st[st.length/2]);
		for (int i = 1; i < st.length/2; i++) {
			p1.add(new Monom(st[i]));
		}
		for (int i = st.length/2+1; i < st.length; i++) {
			p2.add(new Monom(st[i]));
		}
		ComplexFunction cf=new ComplexFunction("plus",p1,p2);
		ComplexFunction co=(ComplexFunction) cf.copy();
		Monom m=new Monom("x");
		cf.div(m);
		//System.out.println(co);
		//System.out.println(cf);
		assertNotEquals(co.f(2),cf.f(2));
		assertEquals(co.f(1),cf.f(1));
	}
	
	@Test
	void ourTest5() throws Exception{
		System.out.println("\nlast TEST: ");
		Polynom p1=new Polynom("x^3 - x^2 + x - 1.0");
		Polynom p2=new Polynom("3.0x^2");
		Monom m1=new Monom("2.0x");
		Monom m2=new Monom("3.0x^2");
		ComplexFunction c1=new ComplexFunction("plus",p1,p2);
		ComplexFunction c2=new ComplexFunction("mul",m1,m2);
		System.out.println(c1);
		System.out.println(c2);
		ComplexFunction cc=new ComplexFunction("divid",c1,c2);
		System.out.println(cc);
		String s=cc.toString();
		function f=cc.initFromString(s);
		System.out.println(f);
		
		ComplexFunction tt=new ComplexFunction("max",cc,c1);
		System.out.println(tt);
		
		assertEquals(tt.f(1),3.0);
	}
	
}
