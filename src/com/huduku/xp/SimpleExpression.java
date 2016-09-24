package com.huduku.xp;

public class SimpleExpression extends MonoExpression {

	public SimpleExpression(String expr) {
		this.expr = expr;
	}
	
	@Override
	public String toString() {
		return expr;
	}

}
