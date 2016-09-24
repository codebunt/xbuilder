package com.huduku.xp;

import java.util.Stack;

/**
 * @author pavithra
 *
 */
public class Expression {

	/**
	 * @param exStr
	 * @return
	 */
	public static Expression get(String exStr) {
		boolean escaping = false;
		exStr = exStr.trim();
		for (int i = 0; i < exStr.length(); i++) {
			if (escaping) {
				escaping = false;
				continue;
			}
			char c = exStr.charAt(i);
			switch (c) {
			case '\\':
				escaping = true;
				break;
			case '>':
				if (i == 0) {
					return new GTExpr(exStr.substring(1).replaceAll("\\\\", ""));
				}
				break;
			case '<':
				if (i == 0) {
					return new LTExpr(exStr.substring(1).replaceAll("\\\\", ""));
				}
				break;
			case '!':
				if (i == 0) {
					return new NotExpr(exStr.substring(1).replaceAll("\\\\", ""));
				}
				break;
			case '*':
				if (i == 0) {
					return new EndsWithExpr(exStr.substring(1).replaceAll("\\\\", ""));
				} else if (i == exStr.length() - 1) {
					return new StartsWithExpr(exStr.substring(0,exStr.length() - 1).replaceAll("\\\\", ""));
				} else {
					return new WildCardExpr(exStr.substring(1).replaceAll("\\\\", ""));
				}
			default:
				break;
			}
		}
		return new SimpleExpression(exStr.replaceAll("\\\\", ""));
	}

	public static ComplexExpr complex(String expr) {
		boolean escaping = false;
		Stack<String> brackets = new Stack<>();
		Stack<ComplexExpr> eStack = new Stack<>();
		String curExpr = "";
		ComplexExpr root = new ComplexExpr();
		ComplexExpr cx = root;
		eStack.push(cx);
		for (int i = 0; i < expr.length(); i++) {
			char c = expr.charAt(i);
			if (escaping) {
				curExpr += c;
				escaping = false;
				continue;
			}
			if (c == '(') {
				brackets.push("(");
				ComplexExpr tmp = new ComplexExpr();
				cx.add(tmp);
				cx = tmp;
				eStack.push(cx);
			} else if (c == ')') {
				if (brackets.size() > 0 && brackets.peek().equals("(")) {
					if (!curExpr.equals("")) {
						cx.add(Expression.get(curExpr));
					}
					curExpr = "";
					brackets.pop();
					eStack.pop();
					cx = eStack.peek();
				} else if (brackets.size() == 0)
					throw new RuntimeException("Mismatch");
			} else if (c == '&' || c == '|') {
				if (!curExpr.equals("")) {
					cx.add(Expression.get(curExpr));
				}
				cx.add(Operand.get(c + ""));
				curExpr = "";
			} else {
				if (c != ' ')
					curExpr += c;
			}
		}
		if (eStack.size() != 1) {
			throw new RuntimeException("Mismatch");
		}
		if (!curExpr.equals(""))
			cx.add(Expression.get(curExpr));
		cx.checkValid();
		return root;
	}

	public static void main(String[] args) {
		System.out.println(complex("(((>123 & !1) | *xyz))"));
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
