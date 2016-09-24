package com.huduku.xp;

public class LTExpr extends MonoExpression {

	public LTExpr(String expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "LESSTHAN("+expr+")";
	}

}
