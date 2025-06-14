/* please note the following: */
/* <function> ::= procedure id ( object <parameters> ) is <stmt-seq> end */

import java.util.*;

/**
 * this is the Function class.
 */
public class Function {
    String name;
    Parameters params;
    StmtSeq stmtSeq;

    /**
     * this is the parse method of the Function class.
     * @params - not currently available.
     */
    public void parse() {
        Parser.expectedToken(Core.PROCEDURE);
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.ID);
        name = Parser.scanner.getId();
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.OBJECT);
        Parser.scanner.nextToken();
        params = new Parameters();
        params.parse();
        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.IS);
        Parser.scanner.nextToken();
        stmtSeq = new StmtSeq();
        stmtSeq.parse();
        Parser.expectedToken(Core.END);
        Parser.scanner.nextToken();
    }

    /**
     * this is the print method of the Function class.
     * @param indent - the number of tabs to indent the Function.
     */
    public void print(int indent) {
        // int j = 0;
        // while (j < indent) {
        //     System.out.print("\t");
        //     j++;
        // }
        // System.out.print("procedure " + name + "(object ");
        // parameters.print(0);
        // System.out.println(") is");
        // stmtSeq.print(indent + 1);
        // j = 0;
        // while (j < indent) {
        //     System.out.print("\t");
        //     j++;
        // }
        // System.out.println("end");

        System.out.print("procedure " + name + " (");
		params.print(0);
		System.out.println(") is ");
		stmtSeq.print(indent + 1);
		System.out.println("end");
    }

    // /**
    //  * this is the execute method of the Function class.
    //  * @params - not currently available.
    //  */
    // public void execute() {
    //     // TODO: we need to implement the execute method...
    //     if (Memory.subroutine.containsKey(name)) {
    //         System.out.println("ERROR: duplicate procedure name. procedure " + "\"" + name + "\"" + " is already defined.");
	// 		System.exit(-1); // exit with an error status...
	// 	}
    //     Memory.subroutine.put(name, this);
    //     parameters.execute();
    // }

    /**
     * this is the execute method of the Function class.
     * @params - not currently available.
     */
    public void execute() {
        if (Memory.map.containsKey(name)) {
            System.out.println("ERROR: duplicate procedure name. procedure " + "\"" + name + "\"" + " is already defined.");
            System.exit(-1); // exit with an error status...
        }
        Memory.map.put(name, this);
        List<String> fp = params.execute();
        HashSet<String> fpSet = new HashSet<String>(fp);
        if (fp.size() != fpSet.size()) {
            System.out.println("ERROR: duplicate formal parameter name. parameter " + "\"" + fp.get(0) + "\"" + " is already defined.");
            System.exit(-1); // exit with an error status...
        }
    }
}
