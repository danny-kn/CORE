/* please note the following: */
/* <stmt-seq> ::= <stmt> | <stmt><stmt-seq> */

import java.util.*;

/**
 * this is the StmtSeq class.
 */
public class StmtSeq {
	Stmt stmt;
	StmtSeq stmtSeq;

	/**
	 * this is the parse method of the StmtSeq class.
	 * @params - not currently available.
	 */
	public void parse() {
		if (Parser.scanner.currentToken() == Core.ID) {
			stmt = new Assign();
		} else if (Parser.scanner.currentToken() == Core.IF) {
			stmt = new If();
		} else if (Parser.scanner.currentToken() == Core.FOR) {
			stmt = new Loop();
		} else if (Parser.scanner.currentToken() == Core.PRINT) {
			stmt = new Print();
		} else if (Parser.scanner.currentToken() == Core.READ) {
			stmt = new Read();
		} else if (Parser.scanner.currentToken() == Core.INTEGER || Parser.scanner.currentToken() == Core.OBJECT) {
			stmt = new Decl();
		} else if (Parser.scanner.currentToken() == Core.BEGIN) {
			stmt = new Call();
		} else {
			// System.out.println("ERROR: not a great start to statement: " + Parser.scanner.currentToken());
			System.out.println("ERROR: missing a procedure body (i.e., no <stmt-seq>).");
			System.exit(-1); // exit with error status...
		}
		stmt.parse();
		if (Parser.scanner.currentToken() != Core.END && Parser.scanner.currentToken() != Core.ELSE) {
			stmtSeq = new StmtSeq();
			stmtSeq.parse();
		}
	}

	/**
	 * this is the print method of the StmtSeq class.
	 * @param indent - the number of tabs to indent the StmtSeq.
	 */
	public void print(int indent) {
		stmt.print(indent);
		if (stmtSeq != null) {
			stmtSeq.print(indent);
		}
	}

	/**
	 * this is the execute method of the StmtSeq class.
	 * @params - not currently available.
	 */
	public void execute() {
		stmt.execute();
		if (stmtSeq != null) {
			stmtSeq.execute();
		}
	}
}
