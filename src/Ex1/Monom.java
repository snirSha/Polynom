/**
 * @author Snir Shaharabani and Omer Kalif
 */
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{

	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}

	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;

	/**
	 * constructor of class Monom 
	 * @param a = _coefficient
	 * @param b = _power
	 */
	public Monom(double a, int b){
		if(a!=0)
			this.set_coefficient(a);
		else
			b=0;

		if(b>=0)
			this.set_power(b);

	}
	/**
	 * copy constructor
	 * @param ot = other Momom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	/**
	 * this function gets a string and change it into Monom (if the string is "ax^b") 
	 * @param s = string of the future monom
	 * @param coe = string of the coefficient
	 * @param pow = string of the power
	 */
	public Monom(String s) throws Exception{
		try {
			if (s == null || s.isEmpty()) 
				throw new IllegalArgumentException("exponent cannot be empty ");
			else {
				s.toLowerCase();
				if(!s.contains("x") && !s.contains("^")) {
					set_power(0);

					if(helpCheckCoe(s))
						set_coefficient(Double.parseDouble(s));
					else throw new IllegalArgumentException("wrong value in string");
				}
				else if(s.equals("x")) {
					set_power(1);
					set_coefficient(1);
				}
				else if(s.equals("-x")) {
					set_power(1);
					set_coefficient(-1);
				}
				else {
					String coe="",pow="";

					if(s.contains("x")&& (s.contains("^"))) {
						coe+=s.substring(0,s.indexOf('x'));
						pow+= s.substring(s.indexOf('^')+1,s.length());
						if(coe.equals(""))
							set_coefficient(1);

						else if(helpCheckCoe(coe) && !coe.equals("-"))
							set_coefficient(Double.parseDouble(coe));
						else if(coe.equals("-"))
							set_coefficient(-1);

						else throw new IllegalArgumentException("wrong value in string");

						if(pow.equals(""))
							throw new IllegalArgumentException("wrong value in power");

						else if(checkIfNum(pow))
							set_power(Integer.parseInt(pow));
						else throw new IllegalArgumentException("wrong value in power");
					}

					else if(s.contains("x")&& !s.contains("^") && (s.indexOf('x')==s.length()-1)) {
						coe+=s.substring(0, s.indexOf('x'));
						if(helpCheckCoe(coe))
							set_coefficient(Double.parseDouble(coe));
						else throw new IllegalArgumentException("wrong value in string");

						set_power(1);
					}
					else throw new IllegalArgumentException("wrong value in string");	
				}

			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	
	/** 
	 * this method returns the derivative monom of this.
	 * @return Monom = new Monom after the derivative
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * @param ans = the number after f(x) 
	 * @param p = _power
	 * @return ans
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 

	/**
	 * @return _coefficient==0 
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	///////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * this is the function that checks if the string is good coefficient 
	 * @param s = the string 
	 * @return = true if the string is coefficient, false if not
	 */
	public boolean helpCheckCoe(String s) {

		if(s.indexOf('-')==0 && s.contains(".")) 
			return (checkIfNum(s.substring(1,s.indexOf('.'))))&&(checkIfNum(s.substring(s.indexOf('.')+1,s.length())));

		if(!s.contains("-")&& s.contains(".")) 
			return (checkIfNum(s.substring(0,s.indexOf('.'))))&&(checkIfNum(s.substring(s.indexOf('.')+1,s.length())));		

		if(s.indexOf('-')==0 && !s.contains(".")) 
			return checkIfNum(s.substring(1,s.length()));

		if(!s.contains("-") && !s.contains("."))
			return checkIfNum(s);

		else return false;
	}

	/**
	 * this function check if the string is a number (char = 0-9) 
	 * @param s = the string
	 * @return true if it is a number, false if not
	 */
	public boolean checkIfNum(String s) {
		if(s.isEmpty())
			return true;
		int i=0;
		while(i<s.length()) {
			if(s.charAt(i)<'0' || s.charAt(i)>'9')
				return false;
			i++;
		}
		return true;
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * add monom with other monom (checks the power)
	 * @param m = monom
	 */
	public void add(Monom m) throws Exception{
		try {
			if(m._power==this._power)
				this._coefficient+=m._coefficient;

			else if(m.get_coefficient()==0) return;

			else throw new RuntimeException("can not add the Monoms because of different powers!!!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * substruct monom by other monom (checks the power)
	 * @param m = monom
	 */
	public void substract(Monom m) throws Exception{
		try {
			if(m._power==this._power)
				this._coefficient -= m._coefficient;
			else throw new RuntimeException("can not substract the Monoms!!!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * multiply monom with other monom 
	 * @param d = monom
	 */
	public void multipy(Monom d) {
		this._coefficient*=d._coefficient;
		this._power+=d._power;
	}
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * this function prints the monom
	 */
	public String toString() {
		if(this.isZero())
			return "0";
		else if(this._power==0 && (this._coefficient==1 || this._coefficient==-1))
			return ""+this._coefficient;
		else if(this._power==0 && this._coefficient!=1 && this._coefficient!=-1)
			return this._coefficient+"";
		else if(this._power==1 && this._coefficient!=1 && this._coefficient!=-1)
			return this._coefficient+"x";
		else if(this._coefficient==1 && this._power>1)
			return "x^"+this._power;
		else if(this._coefficient==1 && this._power==1)
			return "x";
		else if(this._coefficient==-1 && this._power>1)
			return "-x^"+this._power;
		else if(this._coefficient==-1 && this._power==1)
			return "-x";
		else
			return this._coefficient+"x^"+this._power;
	}
	////////////////////////////////////////////////////////////////////////////////////
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	/*Getters & Setters*/
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	
	/**
	 * setters
	 * @param a = a number
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * @param p = a number
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @param m = monom
	 * @return true if the coefficient and the power are equals
	 */
	public boolean equals (Monom m) {
		return Math.abs(this._coefficient - m._coefficient) <= EPSILON && this._power == m._power;
	}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}



	////////////////////////main//////////////////////////
	public static void main (String[] args){
		/*Monom m1=new Monom("2");
		System.out.println(m1.toString());
		Monom m2=new Monom(5,6);
		System.out.println(m2.toString());
		m1.multipy(m2);
		System.out.println("m1*m2= "+ m1);
		Monom m3 = new Monom ("-x^3");
		System.out.println("m3= " + m3.toString());*/
		Monom m1=new Monom(2,3);
		System.out.println(m1.toString());
	}
}