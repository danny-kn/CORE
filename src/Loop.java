/* please note the following: */
/* <loop> ::= for ( id = <expr> ; <cond> ; <expr> ) do <stmt-seq> end */

import java.util.*;

/**
 * this is the Loop class that implements the Stmt interface.
 */
public class Loop implements Stmt {
	Id id;
	Expr initial;
	Cond cond;
	Expr update;
	StmtSeq stmtSeq;

	/**
	 * this is the parse method of the Loop class.
	 * @params - not currently available.
	 */
	public void parse() {
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.LPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.ID);
		id = new Id();
		id.parse();
		Parser.expectedToken(Core.ASSIGN);
		Parser.scanner.nextToken();
		initial = new Expr();
		initial.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
		cond = new Cond();;
		cond.parse();
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
		update = new Expr();
		update.parse();
		Parser.expectedToken(Core.RPAREN);
		Parser.scanner.nextToken();
		Parser.expectedToken(Core.DO);
		Parser.scanner.nextToken();
		stmtSeq = new StmtSeq();
		stmtSeq.parse();
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the Loop class.
	 * @param indent - the number of tabs to indent the Loop.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("for (");
		id.print();
		System.out.print("=");
		initial.print();
		System.out.print(";");
		cond.print();
		System.out.print(";");
		update.print();
		System.out.println(") do");
		stmtSeq.print(indent+1);
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.println("end");
	}

	/**
	 * this is the execute method of the Loop class.
	 * @params - not currently available.
	 */
	public void execute() {
		Memory.store(id.getId(), initial.execute());
		while (cond.execute()) {
			Memory.pushScope();
			stmtSeq.execute();
			Memory.popScope();
			Memory.store(id.getId(), update.execute());
		}
	}
}
