package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import org.hamcrest.core.IsInstanceOf;

public class ComplexFunction implements complex_function {


	private function left;
	private function right;
	private Operation p;

	public ComplexFunction(Operation p , function f1, function f2) {
		this.left=f1;
		this.right=f2;
		this.p=p;
	}

	public ComplexFunction(String str, function p1, function p2) {
		this.left = p1;
		this.right = p2;
		this.p = fromStringToOperator(str);
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
			ans = -1;
			break;

		case Error:
			throw new IllegalArgumentException("The string is wrong!");
		
		default:
			throw new IllegalArgumentException("The string is wrong!");
		}	
		return ans;

	}

	private Operation fromStringToOperator(String operator) {
		switch(operator){
		case "plus":
			return Operation.Plus;
		case "Plus":
			return Operation.Plus;
		case "div":
			return Operation.Divid;
		case "Divid":
			return Operation.Divid;
		case "mul":
			return Operation.Times;
		case "Times":
			return Operation.Times;			
		case "max":
			return Operation.Max;
		case "Max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "Min":
			return Operation.Min;
		case "comp":
			return Operation.Comp;
		case "Comp":
			return Operation.Comp;
		default:
			throw new RuntimeException("bad operation");
		}
	}


	@Override
	public function initFromString(String s) {
		if(s.indexOf('(')==-1) {
			 function f=new Polynom(s);
			 return f;
		}
		int ind1 = s.indexOf('(');
		int ind2 = s.lastIndexOf(',');
		int ind3 = s.lastIndexOf(')');
		String oper = s.substring(0,ind1);
		String lef = s.substring((ind1+1), ind2);
		String rig = s.substring((ind2+1), ind3);
		
		function p1=initFromString(lef);
		function p2=new Polynom(rig);
		
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



	//// private methods ////

	private void makeComplex(function f1) {
		ComplexFunction tmp = new ComplexFunction(this.p,this.left,this.right);
		this.left=tmp;
		this.right=f1;		
	}
}




