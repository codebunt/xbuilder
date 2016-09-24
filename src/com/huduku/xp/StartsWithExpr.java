package com.huduku.xp;

public class StartsWithExpr extends MonoExpression {

	public StartsWithExpr(String expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "STARTSWITH("+expr+")";
	}

}
