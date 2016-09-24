package com.huduku.xp;

public enum Operand {
	AND("&") , 
	OR("|");
	
	private String expr;

	private Operand(String s) {
		this.expr = s;
	}

	public static Operand get(String string) {
		Operand[] values = Operand.values();
		for (int i = 0; i < values.length; i++) {
			if(values[i].expr.equals(string)) return values[i];
		}
		return null;
	}
	
}
