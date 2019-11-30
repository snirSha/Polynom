/**
 * @authors: Omer Kalif && Snir Shaharabani
 */

package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import Ex1.Monom;
import Ex1.Monom_Comperator;
import Ex1.Polynom_able;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able {
	/**
	 * poly is an ArrayList who holds Monoms
	 */

	ArrayList<Monom> poly;

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.poly = new ArrayList<Monom>(0);
	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x",
	 * "(2x^2-4)(-1.2x-7.1)", "(3-3.4x+1)((3.1x-1.2)-(3X^2-3.1))"};
	 * 
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {

		poly = new ArrayList<Monom>();
		s = s.toLowerCase();
		s = s.replaceAll(" ", "");
		s = s.replaceAll("-", "+-");
		String[] new_s = s.split("\\+");
		try {
			if (new_s.length > 1 && new_s[0].isEmpty()) {
				for (int i = 1; i < new_s.length; i++) {
					add(new Monom(new_s[i]));
				}
			} else {
				for (int i = 0; i < new_s.length; i++) {
					add(new Monom(new_s[i]));
				}
			}

			Monom_Comperator s_c = new Monom_Comperator();
			this.poly.sort(s_c);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	///////////////////////////////////////////////////////////////
	@Override
	/**
	 * calculate the value of the func by getting x.
	 */
	public double f(double x) {
		double ans = 0;
		for (int i = 0; i < this.poly.size(); i++) {
			ans += this.poly.get(i).get_coefficient() * Math.pow(x, this.poly.get(i).get_power());
		}

		return ans;
	}

	/////////////////////////////////////////////////////////////////////
	@Override

	/**
	 * Adding Polynom to the existing Polynom
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom tmp = it.next();
			this.add(tmp);
		}
	}

	/////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * Adding Monom m1 to the Polynom
	 * @param m1
	 */
	public void add(Monom m1) {
		try {
			for (int i = 0; i < this.poly.size(); i++) {
				if (this.poly.get(i).get_power() == m1.get_power()) {
					this.poly.get(i).add(m1);
					return;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		this.poly.add(m1);
		Monom_Comperator s_c = new Monom_Comperator();
		this.poly.sort(s_c);
	}

	///////////////////////////////////////////////////////////////////////

	/**
	 * Substract Monom m1 from the Polynom
	 * @param m1
	 */
	public void substract(Monom m1) {
		try {
			for (int i = 0; i < this.poly.size(); i++) {
				if (this.poly.get(i).get_power() == m1.get_power()) {
					this.poly.get(i).substract(m1);
					return;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Monom minus = new Monom(-1, 0);
		m1.multipy(minus);
		this.poly.add(m1);
		Monom_Comperator s_c = new Monom_Comperator();
		this.poly.sort(s_c);
	}

	///////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * Substract Polynom p1 from the Polynom
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> it=p1.iteretor(); 
		while(it.hasNext()) {
			Monom tmp=it.next();
			this.add(new Monom(-1*tmp.get_coefficient(),tmp.get_power()));
		}
		this.remove_Zeroes();
	}

	///////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * Multiply Polynom p1 with the existing Polynom
	 */
	public void multiply(Polynom_able p1) {
		Polynom tmp = new Polynom();
		Iterator<Monom> iter1 = this.iteretor();
		while (iter1.hasNext()) {
			Monom run1 = iter1.next();
			Iterator<Monom> iter2 = p1.iteretor();
			while (iter2.hasNext()) {
				Monom t = new Monom (run1);
				Monom run2 = iter2.next();
				t.multipy(run2);
				tmp.add(t);
			}
		}
		this.poly = tmp.poly;
		poly.sort(new Monom_Comperator());

	}


	/////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * Check if tow Polynoms are equale, return the difference between the powers.
	 */
	public boolean equals(Object p1) {
		if(!(p1 instanceof Polynom)) {
			return false;
		}
		Iterator<Monom> iter1 = this.iteretor();
		Iterator<Monom> iter2 = ((Polynom_able) p1).iteretor();

		while (iter1.hasNext() && iter2.hasNext()) {
			Monom tmp1 = iter1.next();
			Monom tmp2 = iter2.next();
			if ((tmp1.get_coefficient() != tmp2.get_coefficient()) || (tmp1.get_power() != tmp2.get_power()))
				return false;
		}
		return true;
	}

	////////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * return true if the Polynom is ZERO, else retrun false.
	 */
	public boolean isZero() {
		Iterator<Monom> iter = this.iteretor();
		while (iter.hasNext()) {
			if (iter.next().get_coefficient() != 0)
				return false;
		}
		return true;
	}

	///////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * Check if the Polynom is crossing the x line, if so returns the value of x that make the Polynom cross it.
	 */
	public double root(double x0, double x1, double eps){
		try {
			double mid=0;
			if(f(x0) * f(x1) <=0) {
				mid=(x1 + x0)/2; 
				if(Math.abs(f(mid))< eps)
					return mid;

				if(f(x0) == 0)
					return x0;
				if(f(x1) == 0)
					return x1;
				if(f(mid) < 0)
					x0=mid;
				else if (f(mid) > 0)
					x1=mid;
			}
			else {
				throw new IllegalArgumentException("The bounds are invalid, return min value:");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return Integer.MIN_VALUE;
		}
		return root(x0,x1,eps);


	}

	///////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * Copy the Polynom to other Polynom
	 */
	public Polynom_able copy() {
		Iterator<Monom> tmp=this.iteretor();
		Polynom po=new Polynom();
		while(tmp.hasNext()) {
			po.add(tmp.next());
		}
		return po;
	}
	///////////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * return the derivative Polynom
	 */
	public Polynom_able derivative() {
		Polynom po=new Polynom();
		Iterator<Monom> it=this.iteretor();
		while(it.hasNext()) {
			po.add(it.next().derivative());

		}
		po.remove_Zeroes();
		return po;
	}
	//////////////////////////////////////////////////////////////////////////
	//see: https://en.wikipedia.org/wiki/Riemann_integral
	@Override
	/**
	 * calculate the area between the function and the x line
	 */
	public double area(double x0, double x1, double eps) {
		if(x0>=x1)
			return 0;
		double sum=0;
		for(double i=x0; i<x1; i+=eps) {
			sum+=f(i)*eps;
		}
		return sum;
	}
	/////////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * creating iterator
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it=poly.iterator();
		return it;
	}
	/////////////////////////////////////////////////////////////////////////
	@Override
	/**
	 * multiply by Monom m1
	 */
	public void multiply(Monom m1) {
		Polynom tmp=new Polynom();
		if(m1.get_coefficient()==0)
			this.poly.clear();

		else {
			Iterator<Monom> it=this.iteretor();
			Monom t;
			while(it.hasNext()) {
				t=it.next();
				t.multipy(m1);
				tmp.add(t);
			}
		}
		this.poly = tmp.poly;
		this.remove_Zeroes();
		poly.sort(new Monom_Comperator());
	}
	///////////////////////////////////////////////////////////////////////////
	/**
	 * return String of the Polynom
	 */
	public String toString() {
		try {
			if (this.poly.size()==0)
				throw new IllegalArgumentException("The polynom is empty");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		String str="";
		Iterator<Monom> it=this.poly.iterator();
		while(it.hasNext()) {
			str+=it.next().toString();
			if(it.hasNext()) {
				str+=" + ";
			}
		}
		if(str.contains("+ -")){
			str=str.replace("+ -", "- ");
		}
		return str;
	}
	//////////////////////////////////////////////////////////////////////////
	/**
	 * This function help us to remove the zeroes from the Polynom
	 */
	public void remove_Zeroes() {
		Iterator<Monom> iter = iteretor();
		while (iter.hasNext()) {
			Monom runner = iter.next();
			if (runner.get_coefficient() == 0)
				iter.remove();
		}
		Monom_Comperator s_c = new Monom_Comperator();
		this.poly.sort(s_c);
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}
}