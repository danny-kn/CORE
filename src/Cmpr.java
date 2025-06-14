/* please note the following: */
/* <cmpr> ::= <expr> == <expr> | <expr> < <expr> */

import java.util.*;

/**
 * this is the Cmpr class.
 */
public class Cmpr {
	Expr firstExpr;
	Expr secondExpr;
	int option;

	/**
	 * this is the parse method of the Cmpr class.
	 * @params - not currently available.
	 */
	public void parse() {
		firstExpr = new Expr();
		firstExpr.parse();
		if (Parser.scanner.currentToken() == Core.EQUAL) {
			option = 0;
		} else if (Parser.scanner.currentToken() == Core.LESS) {
			option = 1;
		} else {
			System.out.println("ERROR: expected EQUAL or LESS, received " + Parser.scanner.currentToken() + ".");
			System.exit(-1); // exit with an error status...
		}
		Parser.scanner.nextToken();
		secondExpr = new Expr();
		secondExpr.parse();
	}

	/**
	 * this is the print method of the Cmpr class.
	 * @params - not currently available.
	 */
	public void print() {
		firstExpr.print();
		if (option == 0) {
			System.out.print("=");
		} else if (option == 1) {
			System.out.print("<");
		}
		secondExpr.print();
	}

	/**
	 * this is the execute method of the Cmpr class.
	 * @params - not currently available.
	 */
	public boolean execute() {
		boolean result;
		int result1 = firstExpr.execute();
		int result2 = secondExpr.execute();
		if (option == 0) {
			result = result1 == result2;
		} else {
			result = result1 < result2;
		}
		return result;
	}
}
