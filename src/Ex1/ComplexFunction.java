package Ex1;


public class ComplexFunction implements complex_function {
	public static final double EPS = 0.00001;

	private function left;
	private function right;
	private Operation p;

	/*
	 * default constructor
	 */
	public ComplexFunction() {
		left = null;
		right = null;
		p = Operation.Error;
	}

	/*
	 * one parameter constructor
	 */
	public ComplexFunction(function f) {
		this.left = f;
		this.right = null;
		this.p = Operation.None;
	}
	/*
	 * all parameters constructor (with Operation)
	 */
	public ComplexFunction(Operation p , function f1, function f2) {
		if(f2 != null) {
			this.p = p;
		}
		else {
			this.p = Operation.None;
		}
		this.left = f1;
		this.right = f2;
	}

	/*
	 * all parameters constructor (with string of Operation)
	 */
	public ComplexFunction(String str, function p1, function p2) {
		if(p2 != null) {
			this.p = fromStringToOperator(str);			
		}
		else if(p2 == null) {
			this.p=Operation.None;
		}
		this.left = p1;
		this.right = p2;
	}

	/*
	 * calculate the value of the ComplexFunction by getting x

	 */
	public double f(double x) {
		//	Plus, Times, Divid, Max, Min, Comp , None, Error
		double ans = 0;

		switch (p) {
		case Plus:
			ans =  this.left.f(x) + this.right.f(x);
			break;

		case Times:
			ans = this.left.f(x) * this.right.f(x);
			break;

		case Divid:
			if(this.right.f(x) != 0) ans =  this.left.f(x)/this.right.f(x);
			else throw new IllegalArgumentException("Cannot divide by 0!");
			break;

		case Max:
			ans = Math.max(this.left.f(x), this.right.f(x));
			break;

		case Min:
			ans = Math.min(this.left.f(x), this.right.f(x));
			break;

		case Comp:
			ans= this.left.f(this.right.f(x));
			break;

		case None:
			ans= this.left.f(x);
			break;

		case Error:
			throw new IllegalArgumentException("Error!");

		default:
			throw new IllegalArgumentException("The Operation is wrong!");
		}	
		return ans;

	}
	/*
	 *A helper function that turn a string to Operation 
	 */
	private Operation fromStringToOperator(String operator) {
		String s = operator.toLowerCase();

		switch(s){

		case "plus":
			return Operation.Plus;

		case "div":
			return Operation.Divid;

		case "divid":
			return Operation.Divid;

		case "mul":
			return Operation.Times;

		case "times":
			return Operation.Times;

		case "max":
			return Operation.Max;

		case "min":
			return Operation.Min;

		case "comp":
			return Operation.Comp;

		case "none":
			return Operation.None;

		case "error":
			return Operation.Error;

		default:
			throw new RuntimeException("bad operation");
		}
	}

	/*
	 * Take a string and make it ComplexFunction 
	 */
	public function initFromString(String s) {//snir
		if(!isBalanced(s))
			throw new IllegalArgumentException("The delimiters are incorrect");

		if(s.indexOf('(')==-1) {
			function f=new Polynom(s);
			return f;
		}
		int ind1 = s.indexOf('(');
		int ind2 = findTheRightComma(s); 
		int ind3 = s.lastIndexOf(')');

		String rig= s.substring(ind2+2,ind3);
		String lef = s.substring(ind1+1,ind2);
		String oper = s.substring(0,ind1);

		function p1=initFromString(lef);
		function p2=initFromString(rig);
		return new ComplexFunction(oper,p1,p2);		
	}

	/*
	 * Copy a ComplexFunction to another function
	 */
	public function copy() {
		function tmp = new ComplexFunction(this.p, this.left, this.right);
		return tmp;
	}

	/*
	 * this.ComlexFuntion + f1
	 */
	public void plus(function f1) {
		makeComplex(f1);
		this.p=Operation.Plus;
	}
	/*
	 * this.ComlexFuntion * f1
	 */
	public void mul(function f1) {
		makeComplex(f1);
		this.p=Operation.Times;			
	}
	/*
	 * this.ComlexFuntion / f1
	 */
	public void div(function f1) {
		makeComplex(f1);
		this.p=Operation.Divid;
	}
	/*
	 * Max(this.ComlexFuntion,f1)
	 */
	public void max(function f1) {
		makeComplex(f1);
		this.p=Operation.Max;
	}
	/*
	 * Min(this.ComlexFuntion,f1)
	 */
	public void min(function f1) {
		makeComplex(f1);
		this.p=Operation.Min;
	}
	/*
	 * this.ComlexFuntion(f1(x))
	 */
	public void comp(function f1) {
		makeComplex(f1);
		this.p=Operation.Comp;
	}
	/*
	 * return left function
	 */
	public function left() {
		return this.left;
	}
	/*
	 * return right function
	 */
	public function right() {
		return this.right;
	}

	/*
	 * return Operation
	 */
	public Operation getOp() {
		return this.p;
	}
	/*
	 * Print the ComplexFunction-> "Operation(function left,function right)" 
	 */
	public String toString() {
		StringBuilder ans = new StringBuilder();
		if(this.p == Operation.None) {
			ans.append(left.toString());
		}else {
			ans.append(this.p + "(" + this.left.toString() 
			+ " , " + this.right.toString() + ")");
		}

		return ans.toString();
	}

	/*
	 * Times(2x,x)==plus(x^2,x^2)
	 * In this function we check if the two Complexes are equal in the range of [1,10] and [-10,1]
	 */
	public boolean equals(Object obj) {
		if(!(obj instanceof ComplexFunction))
			return false;

		ComplexFunction other = (ComplexFunction) obj;

		for (int i = 1; i <=10; i++) {
			if(Math.abs(this.f(i)-other.f(i))>EPS)
				return false;
		}

		for (int i = -10; i <=1; i++) {
			if(Math.abs(this.f(i)-other.f(i))>EPS)
				return false;
		}
		return true;
	}
	//// private methods ////
	/*
	 * A helper function that make new ComplexFunction from an old ComplexFunction and another function
	 * left <-- old ComplexFunction
	 * right <-- function f1
	 */
	private void makeComplex(function f1) {
		if(f1!=null) {
			ComplexFunction tmp = new ComplexFunction(this.p,this.left,this.right);
			this.left=tmp;
			this.right=f1;
		}else {
			throw new IllegalArgumentException("The cannot operation null");
		}

	}
	/*
	 * Check if the number of opening brackets equals the number of closing brackets
	 */
	private boolean isBalanced(String s) {
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') counter++;
			else if(s.charAt(i) == ')') counter--;
			if(counter < 0) return false;
		}
		return counter == 0;
	}

	/*
	 * Return the Comma that crosses the left and the right functions
	 */
	private int findTheRightComma(String s) {
		int counter=0,i=0;
		for (; i < s.length() && s.charAt(i)!=')' && s.charAt(i+1)!=','; i++) {
			if(s.charAt(i)=='(')
				counter++;
		}

		for (; i < s.length() && counter!=1; i++) {
			if(s.charAt(i)==')')
				counter--;
			if(s.charAt(i)=='(')
				counter++;
		}

		return i+1;
	}

}




