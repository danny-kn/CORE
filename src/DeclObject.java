/* please note the following: */
/* <decl-obj> ::= object id ; */

import java.util.*;

/**
 * this is the DeclObject class.
 */
public class DeclObject {
	Id id;

	/**
	 * this is the parse method of the DeclObject class.
	 * @params - not currently available.
	 */
	public void parse() {
		Parser.expectedToken(Core.OBJECT);
		Parser.scanner.nextToken();
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the DeclObject class.
	 * @param indent - the number of tabs to indent the DeclObject.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("object ");
		id.print();
		System.out.println(";");
	}

	/**
	 * this is the execute method of the DeclObject class.
	 * @params - not currently available.
	 */
	public void execute() {
		Memory.declareObject(id.getId());
	}
}
