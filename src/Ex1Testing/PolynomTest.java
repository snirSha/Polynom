package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;

class PolynomTest {

	@Test
	void sortToStringTest() throws Exception {
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
	void basicMathTest() {
		Polynom p1 = new Polynom("1.5x^2 + x + 1.0");
		Polynom p2 = new Polynom("0.3x^2 + 4x^3 +5.5 + 2x");
		Polynom p3 = new Polynom("4X^2 -8");
		p1.substract(p2);
		String expected1 = "-4.0x^3 + 1.2x^2 - x - 4.5";
		assertEquals(expected1, p1.toString());
		p1.multiply(p3);
		String expected2 = "-16.0x^5 + 4.8x^4 + 28.0x^3 - 27.6x^2 + 8.0x + 36.0";
		assertEquals(expected2, p1.toString());
		Monom m = new Monom(3, 2);
		p1.add(m);
		String expected3 = "-16.0x^5 + 4.8x^4 + 28.0x^3 - 24.6x^2 + 8.0x + 36.0";
		assertEquals(expected3, p1.toString());
		Polynom p4 = new Polynom("2x^4 + 3");
		p4.multiply(m);
		String expected4 = "6.0x^6 + 9.0x^2";
		assertEquals(expected4, p4.toString());
	}
	@Test
	void derivativeTest() {
		Polynom p1 = new Polynom("x^9 -2x^4 + 8x - 9");
		p1 = (Polynom) p1.derivative();
		String expected2 = "9.0x^8 - 8.0x^3 + 8.0";
		assertEquals(expected2, p1.toString());
	}
	@Test
	void equalsTest() {
		Polynom p1 = new Polynom("x^9 -2x^4 + 8x - 9");
		Polynom p2 = new Polynom("-9 + x^9 + 8x -2x^4"); //same polynom as p1
		boolean expected = true;
		assertEquals(expected, p1.equals(p2));
		p2 = (Polynom) p2.derivative();
		assertNotEquals(expected, p1.equals(p2));
	}
	@Test
	void isZeroTest() {
		Polynom p1 = new Polynom("x^9 -2x^4 + 8x - 9");
		boolean expected = false;
		assertEquals(expected, p1.isZero());
		p1.substract(new Polynom("x^9 -2x^4 + 8x - 9"));
		assertNotEquals(expected, p1.isZero());
	}
	@Test
	void rootTest() {
		Polynom p1 = new Polynom("2x^2 -3x - 2");
		double expected = -0.5;
		assertEquals(expected, p1.root(-1, 0, 0.000001));
	}
	@Test
	void copyTest() {
		Polynom p1 = new Polynom("2x^2 -3x - 2");
		Polynom p2 = (Polynom) p1.copy();
		assertEquals(p1.equals(p2), true);
	}
	@Test
	void areaTest() {
		Polynom p1 = new Polynom("2x");
		assertEquals(Math.abs(p1.area(0, 4, 0.00001) - 16) < 0.0001, true);
	}


}