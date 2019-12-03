package Ex1;


public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation p;

	public ComplexFunction(function f) {
		this.left=f;
	}
	
	public ComplexFunction(Operation p , function f1, function f2) {
		if(f2!=null) {
			this.p=p;
		}
		else if(f2==null) {
			this.p=Operation.None;
		}
		this.left=f1;
		this.right=f2;
	}

	public ComplexFunction(String str, function p1, function p2) {
		if(p2!=null) {
			this.p = fromStringToOperator(str);			
		}
		else if(p2==null) {
			this.p=Operation.None;
		}
		this.left=p1;
		this.right=p2;
	}

	@Override
	public double f(double x) {
		//	Plus, Times, Divid, Max, Min, Comp , None, Error
		double ans = 0;
		
		switch (p) {
		case Plus:
			ans =  this.left.f(x)+this.right.f(x);
			break;

		case Times:
			ans =  this.left.f(x)*this.right.f(x);
			break;

		case Divid:
			ans =  this.left.f(x)/this.right.f(x);
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
			throw new IllegalArgumentException("Wrong value!");
		
		default:
			throw new IllegalArgumentException("The string is wrong!");
		}	
		return ans;

	}

	private Operation fromStringToOperator(String operator) {
		String s=operator.toLowerCase();
		
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
			
		default:
			throw new RuntimeException("bad operation");
		}
	}


	@Override
	public function initFromString(String s) {
		if(!isBalanced(s))
			throw new IllegalArgumentException("The delimiters are incorrect");

		if(s.indexOf('(')==-1) {
			 function f=new Polynom(s);
			 return f;
		}
		int ind1 = s.indexOf('(');
		int ind2 = s.indexOf(',');
		int indLast2=s.lastIndexOf(','); 
		int ind3 = s.lastIndexOf(')');
		
		String rig="",lef="";
		
		if(s.charAt(ind2+2)<='z'&&s.charAt(ind2+2)>='A') {
			rig = s.substring((ind2+2), ind3);
			lef = s.substring(ind1+1, ind2);
		}
		else {
			rig= s.substring(indLast2+2,ind3);
			lef = s.substring(ind1+1,indLast2);
		}
		System.out.println(lef+ " | "+rig);
		String oper = s.substring(0,ind1);
		
		function p1=initFromString(lef);
		function p2=initFromString(rig);
		return new ComplexFunction(oper,p1,p2);		
	}

	@Override
	public function copy() {
		function tmp = new ComplexFunction(this.p, this.left, this.right);
		return tmp;
	}

	@Override
	public void plus(function f1) {
		makeComplex(f1);
		this.p=Operation.Plus;
	}

	@Override
	public void mul(function f1) {
		makeComplex(f1);
		this.p=Operation.Times;			
	}

	@Override
	public void div(function f1) {
		makeComplex(f1);
		this.p=Operation.Divid;
	}

	@Override
	public void max(function f1) {
		makeComplex(f1);
		this.p=Operation.Max;
	}

	@Override
	public void min(function f1) {
		makeComplex(f1);
		this.p=Operation.Min;
	}

	@Override
	public void comp(function f1) {
		makeComplex(f1);
		this.p=Operation.Comp;
	}


	@Override
	public function left() {
		return this.left;
	}

	@Override
	public function right() {
		return this.right;
	}

	@Override
	public Operation getOp() {
		return this.p;
	}
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append(this.p+"("+this.left.toString()+" , "+this.right.toString()+")");
		return ans.toString();
	}

	/*
	 * Times(2x,x)==plus(x^2,x^2)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof ComplexFunction))
			return false;

		ComplexFunction other = (ComplexFunction) obj;

		for (int i = 1; i <=10; i++) {
			if(this.f(i)!=other.f(i))
				return false;
		}
		return true;
	}
	//// private methods ////
	
	private void makeComplex(function f1) {
		ComplexFunction tmp = new ComplexFunction(this.p,this.left,this.right);
		this.left=tmp;
		this.right=f1;		
	}
	
	public static boolean isBalanced(String s) {
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') counter++;
			else if(s.charAt(i) == ')') counter--;
			if(counter < 0) return false;
		}
		return counter == 0;
	}
}




