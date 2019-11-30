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
			ans =  Math.max(this.left.f(x), this.right.f(x));
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
		}
		return ans;
	
	}
	
	private Operation fromStringToOperator(String operator) {
		switch(operator){
		case "plus":
			return Operation.Plus;
		case "div":
			return Operation.Divid;
		case "mul":
			return Operation.Times;
		case "max":
			return Operation.Max;
		case "min":
			return Operation.Min;
		case "comp":
			return Operation.Comp;
		default:
			throw new RuntimeException("bad operator");
		}
	}


	@Override
	public function initFromString(String s) {
		//Times(Plus(2.0x^2 , 3.0x^3),3.0x^3)
		//String [] str=s.split("("|"//)"); 
		 int index=s.indexOf('(');
		 String oper = s.substring(0,index);
		return null;
	}

	@Override
	public function copy() {
		function tmp = new ComplexFunction(this.p,this.left, this.right);
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




