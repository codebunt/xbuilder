package com.huduku.xp;

public class EndsWithExpr extends MonoExpression {

	public EndsWithExpr(String expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return "ENDSWITH("+expr+")";
	}

}
