/* please note the following: */
/* <procedure> ::= procedure id is <decl-seq> begin <stmt-seq> end | procedure id is begin <stmt-seq> end */

import java.util.*;

/**
 * this is the Procedure class.
 */
public class Procedure {
	String name;
	DeclSeq declSeq;
	StmtSeq stmtSeq;

	/**
	 * this is the parse method of the Procedure class.
	 * @params - not currently available.
	 */
	public void parse() {
		Parser.expectedToken(Core.PROCEDURE);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.ID);
		name = Parser.scanner.getId();
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.IS);
		Parser.scanner.nextToken();
		if (Parser.scanner.currentToken() != Core.BEGIN) {
			declSeq = new DeclSeq();
			declSeq.parse();
		}
		Parser.expectedToken(Core.BEGIN);
		Parser.scanner.nextToken();
		stmtSeq = new StmtSeq();
		stmtSeq.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.EOS);
	}

	/**
	 * this is the print method of the Procedure class.
	 * @params - not currently available.
	 */
	public void print() {
		System.out.println("procedure " + name + " is");
		if (declSeq != null) {
			declSeq.print(1);
		}
		System.out.println("begin ");
		stmtSeq.print(1);
		System.out.println("end");
	}

	/**
	 * this is the execute method of the Procedure class.
	 * @params - not currently available.
	 */
	public void execute() {
		if (declSeq != null) {
			Memory.initializeGlobal();
			declSeq.execute();
		}
		Memory.initializeLocal();
		Memory.pushScope();
		stmtSeq.execute();
		Memory.popScope();
		// please note that i added the following line of code:
		Memory.clearGlobal();
	}
}
