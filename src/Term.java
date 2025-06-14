
/**
 * this is the Term class.
 */
public class Term {
	Factor factor;
	Term term;
	int option;

	/**
	 * this is the parse method of the Term class.
	 * @param - not currently available.
	 */
	public void parse() {
		factor  = new Factor();
		factor.parse();
		if (Parser.scanner.currentToken() == Core.MULTIPLY) {
			option = 1;
		} else if (Parser.scanner.currentToken() == Core.DIVIDE) {
			option = 2;
		}
		if (option != 0) {
			Parser.scanner.nextToken();
			term = new Term();
			term.parse();
		}
	}

	/**
	 * this is the print method of the Term class.
	 * @param - not currently available.
	 */
	public void print() {
		factor.print();
		if (option == 1) {
			System.out.print("*");
			term.print();
		} else if (option == 2) {
			System.out.print("/");
			term.print();
		}
	}

	/**
	 * this is the execute method of the Term class.
	 * @param - not currently available.
	 */
	public int execute() {
		int value = factor.execute();
		if (option == 1) {
			value *= term.execute();
		} else if (option == 2) {
			int denom = term.execute();
			if (denom == 0) {
				System.out.println("ERROR: cannot divide by zero!");
				System.exit(-1); // exit with an error status...
			}
			value /= denom;
		}
		return value;
	}
}
