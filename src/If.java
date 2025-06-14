import java.util.*;

/**
 * this is the If class that implements the Stmt interface.
 */
public class If implements Stmt {
	Cond cond;
	StmtSeq firstStmtSeq;
	StmtSeq secondStmtSeq;

	/**
	 * this is the parse method of the If class.
	 * @params - not currently available.
	 */
	public void parse() {
		Parser.scanner.nextToken();
		cond = new Cond();
		cond.parse();
		Parser.expectedToken(Core.THEN);
		Parser.scanner.nextToken();
		firstStmtSeq = new StmtSeq();
		firstStmtSeq.parse();
		if (Parser.scanner.currentToken() == Core.ELSE) {
			Parser.scanner.nextToken();
			secondStmtSeq = new StmtSeq();
			secondStmtSeq.parse();
		}
		Parser.expectedToken(Core.END);
		Parser.scanner.nextToken();
	}

	/**
	 * this is the print method of the If class.
	 * @param indent - the number of tabs to indent the If.
	 */
	public void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("	");
		}
		System.out.print("if ");
		cond.print();
		System.out.println(" then");
		firstStmtSeq.print(indent + 1);
		if (secondStmtSeq != null) {
			for (int i = 0; i < indent; i++) {
				System.out.print("	");
			}
			System.out.println("else");
			secondStmtSeq.print(indent+1);
		}
		for (int i = 0; i < indent; i++) {
			System.out.print("	");
		}
		System.out.println("end");
	}

	/**
	 * this is the execute method of the If class.
	 * @params - not currently available.
	 */
	public void execute() {
		if (cond.execute()) {
			Memory.pushScope();
			firstStmtSeq.execute();
			Memory.popScope();
		} else if (secondStmtSeq != null) {
			Memory.pushScope();
			secondStmtSeq.execute();
			Memory.popScope();
		}
	}
}
