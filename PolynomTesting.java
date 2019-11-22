package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTesting {

	@Test
	void test1() throws Exception {//testing the following func: sort, toString
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		String expected = "1.5x^2 + x + 1.0";
		assertEquals(expected, p1.toString());
	}
	@Test
	void test2() { //testing the following func: add, substract, multiply
		Polynom p1 = new Polynom("1.5x^2 + x + 1.0");
		Polynom p2 = new Polynom("0.3x^2 + 4x^3 +5.5 + 2x");
		Polynom p3 = new Polynom("4X^2 -8");
		p1.substract(p2);
		String expected2 = "-4.0x^3 + 1.2x^2 - x - 4.5";
		assertEquals(expected2, p1.toString());
		p1.multiply(p3);
		String expected3 = "-16.0x^5 + 4.8x^4 + 28.0x^3 - 27.6x^2 + 8.0x + 36.0";
		assertEquals(expected3, p1.toString());
		Monom m = new Monom(3, 2);
		p1.add(m);
		String expected4 = "-16.0x^5 + 4.8x^4 + 28.0x^3 - 24.6x^2 + 8.0x + 36.0";
		assertEquals(expected4, p1.toString());
	}
	@Test
	void test3() { //testing the following func: derivative
		Polynom p1 = new Polynom("x^9 -2x^4 + 8x - 9");
		p1 = (Polynom) p1.derivative();
		System.out.println(p1);
		String expected2 = "9.0x^8 - 8.0x^3 + 8.0";
		assertEquals(expected2, p1.toString());
		
	}

}
