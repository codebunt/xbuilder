package com.huduku.xp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexExpr extends Expression {

	private Operand op;

	private List<? super Expression> list = new ArrayList<>();

	public ComplexExpr() {
	}

	public void add(Expression expression) {
		if (op == null && list.size() > 0)
			throw new RuntimeException("xxx ");
		list.add(expression);
	}

	public void add(Operand operator) {
		if (op != null && op != operator) {
			throw new RuntimeException("yyy");
		}
		if (list.size() == 0)
			throw new RuntimeException("zzz");
		op = operator;
	}

	@Override
	public String toString() {
		boolean start = true;
		boolean wrapWithBrackets = (op != null);
		String res = wrapWithBrackets ? "(" : "";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Expression expr = (Expression) iterator.next();
			if (!start)
				res = res + " " + op.toString() + " " + expr.toString();
			else
				res = res + expr.toString();
			start = false;
		}
		return res + (wrapWithBrackets ? ")" : "");
	}

	public void checkValid() {
		if ((list.size() > 1 && op == null) || (list.size() <= 1 && op != null)) {
			throw new RuntimeException("mismatch");
		}
	}
}
