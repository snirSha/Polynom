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
		Monom m1 = new Monom(2,2);//2x^2
		Monom m2 = new Monom(3,3);//3x^3
		ComplexFunction cf = new ComplexFunction("plus", m1, m2);//3x^3 + 2x^2
	//	System.out.println(cf);
		cf.mul(m2);
		System.out.println(cf);//9x^6 + 6x^5
		Polynom p = new Polynom();
		p.add(m1);
		p.add(m2);
		p.multiply(m2);//9x^6 + 6x^5
		double v = 4.0;
		double dp = p.f(v);//43008
		double dcf = cf.f(v);//43008
		double dd = Math.abs(dp-dcf);//0
		if(dd>EPS) {
			System.out.println(p+" at "+v+" = "+dp);
			System.out.println(cf+" at "+v+" = "+dcf);
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
			ComplexFunction cf = new ComplexFunction("plus", m1,m2);//3.0x^3 , 2.0x^2
			ComplexFunction cf3 = new ComplexFunction("plus", p1,p2);//3.0x^3 , 2.0x^2
			//System.out.println(cf);
			cf.mul(m2);//9x^6 + 6x^5
			cf3.mul(m2);//9x^6 + 6x^5
			String s = cf.toString();
			function cf2 = cf.initFromString(s);//9x^6 + 6x^5
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
		System.out.println(cf4);
		System.out.println(cf5);
	}
}
