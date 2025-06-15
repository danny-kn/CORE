/* please note the following: */
/* <read> ::= read ( id ) ; */

import java.util.*;

/**
 * this is the Read class that implements the Stmt interface.
 */
public class Read implements Stmt {
	Id argument;

	/**
	 * this is the parse method of the Read class.
	 * @param - not currently available.
	 */
	public void parse() {
		Parser.expectedToken(Core.READ);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		argument = new Id();
		argument.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the Read class.
	 * @param indent - the number of tabs to indent the Read.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("read(");
		argument.print();
		System.out.println(");");
	}

	/**
	 * this is the execute method of the Read class.
	 * @param - not currently available.
	 */
	public void execute() {
		if (Memory.data == null) {
			System.out.println("ERROR: no data file provided for read operation!");
			System.exit(-1); // exit with an error status...
		}
		if (Memory.data.currentToken() == Core.EOS) {
			System.out.println("ERROR: the data file is out of values!");
			System.exit(-1); // exit with an error status...
		}
		int value = Memory.data.getConst();
		Memory.store(argument.getId(), value);
		Memory.data.nextToken();
	}
}
