/* please note the following: */
/* <decl> ::= <decl-integer> | <decl-obj> */

import java.util.*;

/**
 * this is the Decl class that implements the Stmt interface.
 */
public class Decl implements Stmt {
	DeclInteger declInteger;
	DeclObject declObject;

	/**
	 * this is the parse method of the Decl class.
	 * @params - not currently available.
	 */
	public void parse() {
		if (Parser.scanner.currentToken() == Core.INTEGER) {
			declInteger = new DeclInteger();
			declInteger.parse();
		} else {
			declObject = new DeclObject();
			declObject.parse();
		}
	}

	/**
	 * this is the print method of the Decl class.
	 * @param indent - the number of tabs to indent the Decl.
	 */
	public void print(int indent) {
		if (declInteger != null) {
			declInteger.print(indent);
		} else {
			declObject.print(indent);
		}
	}

	/**
	 * this is the execute method of the Decl class.
	 * @params - not currently available.
	 */
	public void execute() {
		if (declInteger != null) {
			declInteger.execute();
		} else {
			declObject.execute();
		}
	}
}
