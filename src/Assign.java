/* please note the following: */
/* <assign> ::= id = <expr> ; | id [ string ] = <expr> ; | id = new object( string, <expr> ); | id : id ; */

import java.util.*;

/**
 * this is the Assign class that implements the Stmt interface.
 */
public class Assign implements Stmt {
	/* 0 if id := <expr> assignment. */
	/* 1 if id[string] := <expr> assignment. */
	/* 2 if "new" assignment. */
	/* 3 if "alias" assignment. */
	int type;
	/* assignTo is the id on the LHS of the assignment. */
	Id assignTo;
	/* key is the string in the "new" assignment or id[string] assignment. */
	String key;
	/* init is the expression in the "new" assignment. */
	Expr init;
	/* assignFrom is the id on the RHS of the "alias" assignment. */
	Id assignFrom;
	/* expr is the RHS expression in an assignment. */
	Expr expr;

	/**
	 * this is the parse method of the Assign class.
	 * @params - not currently available.
	 */
	public void parse() {
		type = 0;
		assignTo = new Id();
		assignTo.parse();
		if (Parser.scanner.currentToken() == Core.LSQUARE) {
			type = 1;
			Parser.scanner.nextToken();
			key = Parser.scanner.getString();
			Parser.scanner.nextToken();
			Parser.expectedToken(Core.RSQUARE);
			Parser.scanner.nextToken();
		}
		if (Parser.scanner.currentToken() == Core.ASSIGN) {
			Parser.scanner.nextToken();
			if (Parser.scanner.currentToken() == Core.NEW) {
				type = 2;
				Parser.scanner.nextToken();
				Parser.expectedToken(Core.OBJECT);
				Parser.scanner.nextToken();
				Parser.expectedToken(Core.LPAREN);
				Parser.scanner.nextToken();
				Parser.expectedToken(Core.STRING);
				key = Parser.scanner.getString();
				Parser.scanner.nextToken();
				Parser.expectedToken(Core.COMMA);
				Parser.scanner.nextToken();
				init = new Expr();
				init.parse();
				Parser.expectedToken(Core.RPAREN);
				Parser.scanner.nextToken();
			} else {
				expr = new Expr();
				expr.parse();
			}
		} else {
			Parser.expectedToken(Core.COLON);
			Parser.scanner.nextToken();
			type = 3;
			assignFrom = new Id();
			assignFrom.parse();
		}
		Parser.expectedToken(Core.SEMICOLON);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the Assign class.
	 * @param indent - the number of tabs to indent the Assign.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		assignTo.print();
		if (type == 1) {
			System.out.print("['" + key + "']");
		}
		if (type == 0 || type == 1) {
			System.out.print("=");
			expr.print();
		} else if (type == 2) {
			System.out.print("=new object('" + key + "'");
			System.out.print(",");
			init.print();
			System.out.print(")");
		} else if (type == 3) {
			System.out.print(":");
			assignFrom.print();
		}
		System.out.println(";");
	}

	/**
	 * this is the execute method of the Assign class.
	 * @params - not currently available.
	 */
	public void execute() {
		if (type == 0) {
			Memory.store(assignTo.getId(), expr.execute());
		} else if (type == 1) {
			Memory.store(assignTo.getId(), key, expr.execute());
		} else if (type == 2) {
			Memory.allocate(assignTo.getId(), key, init.execute());
		} else if (type == 3) {
			Memory.alias(assignTo.getId(), assignFrom.getId());
		}
	}
}
