package com.huduku.xp;

public class GTExpr extends MonoExpression {

	public GTExpr(String expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "GT("+expr+")";
	}

}
