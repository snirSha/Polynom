package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTesting {

	@Test
	void test() throws Exception {
		String[] monoms = {"2.0", "-x","-3.2x^2","0"};
		Monom m;
		for (int i = 0; i < monoms.length; i++) {
			m=new Monom(monoms[i]);
			assertEquals(m.toString(),monoms[i]);
		}
	}
	@Test
	void addTest() throws Exception {
		String[] goodMonoms = {"2x^3","8x^3","4x^4","156x^4","-1","0"};
		Monom [] m=new Monom[6];
		for (int i = 0; i < goodMonoms.length; i++) {
			m[i]=new Monom(goodMonoms[i]);
		}
		m[0].add(m[1]);
		m[2].add(m[3]);
		m[4].add(m[5]);
		assertEquals(m[0].toString(),"10.0x^3");
		assertEquals(m[2].toString(),"160.0x^4");
		assertEquals(m[4].toString(),"-1.0");
	}
	
	@Test
	void substracTest() throws Exception {
		String[] goodMonoms = {"4x^6","15x^6","24x^4","24x^4","-121.5x","-56.5x"};
		Monom [] m=new Monom[6];
		for (int i = 0; i < goodMonoms.length; i++) {
			m[i]=new Monom(goodMonoms[i]);
		}
		m[0].substract(m[1]);
		m[2].substract(m[3]);
		m[4].substract(m[5]);
		assertEquals(m[0].toString(),"-11.0x^6");
		assertEquals(m[2].toString(),"0");
		assertEquals(m[4].toString(),"-65.0x");
	}
	
	@Test
	void multipyTest() throws Exception {
		String[] goodMonoms = {"2x^6","8x^3","4x","-8","-1x^123","-56.5x"};
		Monom [] m=new Monom[6];
		for (int i = 0; i < goodMonoms.length; i++) {
			m[i]=new Monom(goodMonoms[i]);
		}
		m[0].multipy(m[1]);
		m[2].multipy(m[3]);
		m[4].multipy(m[5]);
		assertEquals(m[0].toString(),"16.0x^9");
		assertEquals(m[2].toString(),"-32.0x");
		assertEquals(m[4].toString(),"56.5x^124");
	}
	
	@Test
	void derivativeTest() throws Exception {
		String[] goodMonoms = {"-2x^3","12x^3","4x","-8","-1x^12","-56.5x"};
		Monom [] m=new Monom[6];
		for (int i = 0; i < goodMonoms.length; i++) {
			m[i]=new Monom(goodMonoms[i]);
		}

		assertEquals(m[0].derivative().toString(),"-6.0x^2");
		assertEquals(m[1].derivative().toString(),"36.0x^2");
		assertEquals(m[2].derivative().toString(),"4.0");
		assertEquals(m[3].derivative().toString(),"0");
		assertEquals(m[4].derivative().toString(),"-12.0x^11");
		assertEquals(m[5].derivative().toString(),"-56.5");
	}
	
	

}
