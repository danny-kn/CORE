/* please note the following: */
/* <expr> ::= <term> | <term> + <expr> | <term> – <expr> */

import java.util.*;

/**
 * this is the Expr class.
 */
public class Expr {
	Term term;
	Expr expr;
	int option;

	/**
	 * this is the parse method of the Expr class.
	 * @params - not currently available.
	 */
	public void parse() {
		term  = new Term();
		term.parse();
		if (Parser.scanner.currentToken() == Core.ADD) {
			option = 1;
		} else if (Parser.scanner.currentToken() == Core.SUBTRACT) {
			option = 2;
		}
		if (option != 0) {
			Parser.scanner.nextToken();
			expr = new Expr();
			expr.parse();
		}
	}

	/**
	 * this is the print method of the Expr class.
	 * @param indent - the number of tabs to indent the Expr.
	 */
	public void print() {
		term.print();
		if (option == 1) {
			System.out.print("+");
			expr.print();
		} else if (option == 2) {
			System.out.print("-");
			expr.print();
		}
	}

	/**
	 * this is the execute method of the Expr class.
	 * @params - not currently available.
	 */
	public int execute() {
		int value = term.execute();
		if (option == 1) {
			value += expr.execute();
		} else if (option == 2) {
			value -= expr.execute();
		}
		return value;
	}
}
