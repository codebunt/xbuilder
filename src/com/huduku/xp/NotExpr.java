package com.huduku.xp;

public class NotExpr extends MonoExpression {

	public NotExpr(String expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "NOT("+expr+")";
	}

}
