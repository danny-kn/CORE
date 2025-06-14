/* please note the following: */
/* <factor> ::= id | id [ string ] | const | ( <expr> ) */

import java.util.*;

/**
 * this is the Factor class.
 */
public class Factor {
	Id id;
	String key;
	int constant;
	Expr expr;

	/**
	 * this is the parse method of the Factor class.
	 * @params - not currently available.
	 */
	public void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			id = new Id();
			id.parse();
			if (Parser.scanner.currentToken() == Core.LSQUARE) {
				Parser.scanner.nextToken();
				key = Parser.scanner.getString();
				Parser.scanner.nextToken();
				Parser.expectedToken(Core.RSQUARE);
				Parser.scanner.nextToken();
			}
		} else if (Parser.scanner.currentToken() == Core.CONST) {
			constant = Parser.scanner.getConst();
			Parser.scanner.nextToken();
		} else if (Parser.scanner.currentToken() == Core.LPAREN) {
			Parser.scanner.nextToken();
			expr = new Expr();
			expr.parse();
			Parser.expectedToken(Core.RPAREN);
			Parser.scanner.nextToken();
		} else {
			System.out.println("ERROR: expected ID, CONST, LPAREN, received " + Parser.scanner.currentToken() + ".");
			System.exit(-1); // exit with an error status...
		}
	}

	/**
	 * this is the print method of the Factor class.
	 * @param indent - the number of tabs to indent the Factor.
	 */
	public void print() {
		if (id != null) {
			id.print();
			if (key != null) {
				System.out.print("['" + key + "']");
			}
		} else if (expr != null) {
			System.out.print("(");
			expr.print();
			System.out.print(")");
		} else {
			System.out.print(constant);
		}
	}

	/**
	 * this is the execute method of the Factor class.
	 * @params - not currently available.
	 */
	public int execute() {
		int value;
		if (id != null && key != null) {
			value = Memory.load(id.getId(), key);
		} else if (id != null) {
			value = Memory.load(id.getId());
		} else if (expr != null) {
			value = expr.execute();
		} else if (constant == -1) {
			if (Memory.data != null && Memory.data.currentToken() == Core.CONST) {
				value = Memory.data.getConst();
				Memory.data.nextToken();
			} else {
				value = -1;
				System.out.println("ERROR: data file not provided or out of values!");
				System.exit(1);
			}
		} else {
			value = constant;
		}
		return value;
	}
}
