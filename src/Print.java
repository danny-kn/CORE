/* please note the following: */
/* <print> ::= print ( <expr> ) ; */

import java.util.*;

/**
 * this is the Print class that implements the Stmt interface.
 */
public class Print implements Stmt {
	Expr expr;

	/**
	 * this is the parse method of the Print class.
	 * @param - not currently available.
	 */
	public void parse() {
		Parser.expectedToken(Core.PRINT);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		expr = new Expr();
		expr.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the Print class.
	 * @param indent - the number of tabs to indent the Print.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("print(");
		expr.print();
		System.out.println(");");
	}

	/**
	 * this is the execute method of the Print class.
	 * @param - not currently available.
	 */
	public void execute() {
		System.out.println(expr.execute());
	}
}
