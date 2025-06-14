/* please note the following: */
/* <decl-integer> ::= integer id ; */

import java.util.*;

/**
 * this is the DeclInteger class.
 */
public class DeclInteger {
	Id id;

	/**
	 * this is the parse method of the DeclInteger class.
	 * @params - not currently available.
	 */
	public void parse() {
		Parser.expectedToken(Core.INTEGER);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the DeclInteger class.
	 * @param indent - the number of tabs to indent the DeclInteger.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("integer ");
		id.print();
		System.out.println(";");
	}

	/**
	 * this is the execute method of the DeclInteger class.
	 * @params - not currently available.
	 */
	public void execute() {
		Memory.declareInteger(id.getId());
	}
}
