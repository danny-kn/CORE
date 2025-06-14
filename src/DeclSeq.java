/* please note the following: */
/* <decl-seq> ::= <decl > | <decl><decl-seq> | <function> | <function><decl-seq> */

import java.util.*;

/**
 * this is the DeclSeq class.
 */
public class DeclSeq {
	Decl decl;
	DeclSeq declSeq;
	Function func;

	/**
	 * this is the parse method of the DeclSeq class.
	 * @params - not currently available.
	 */
	public void parse() {
		if (Parser.scanner.currentToken() == Core.PROCEDURE) {
			func = new Function();
			func.parse();
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				declSeq = new DeclSeq();
				declSeq.parse();
			}
		} else {
			decl = new Decl();
			decl.parse();
			if (Parser.scanner.currentToken() != Core.BEGIN) {
				declSeq = new DeclSeq();
				declSeq.parse();
			}
		}
	}

	/**
	 * this is the print method of the DeclSeq class.
	 * @param indent - the number of tabs to indent the DeclSeq.
	 */
	public void print(int indent) {
		if (func != null) {
			func.print(indent);
		} else {
			decl.print(indent);
		}
		if (declSeq != null) {
			declSeq.print(indent);
		}
	}

	/**
	 * this is the execute method of the DeclSeq class.
	 * @params - not currently available.
	 */
	public void execute() {
		if (func != null) {
			func.execute();
		} else {
			decl.execute();
		}
		if (declSeq != null) {
			declSeq.execute();
		}
	}
}
