/* please note the following: */
/* <id> ::= id */

import java.util.*;

/**
 * this is the Id class.
 */
public class Id {
	String identifier;

	/**
	 * this is the parse method of the Id class.
	 * @params - not currently available.
	 */
	public void parse() {
		Parser.expectedToken(Core.ID);
		identifier = Parser.scanner.getId();
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the Id class.
	 * @param indent - the number of tabs to indent the Id.
	 */
	public void print() {
		System.out.print(identifier);
	}

	/**
	 * this is the getId method of the Id class.
	 * @return - the identifier of the Id.
	 */
	public String getId() {
		return identifier;
	}
}
