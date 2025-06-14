/* please note the following: */
/* <call> ::= begin id ( <parameters> ); */

import java.util.*;

/**
 * this is the Call class that implements the Stmt interface.
 */
public class Call implements Stmt {
    String name;
    Parameters params;

    /**
     * this is the parse method of the Call class.
     * @params - not currently available.
     */
    public void parse() {
        Parser.expectedToken(Core.BEGIN);
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.ID);
        name = Parser.scanner.getId();
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.LPAREN);
        Parser.scanner.nextToken();
        params = new Parameters();
        params.parse();
        Parser.expectedToken(Core.RPAREN);
        Parser.scanner.nextToken();
        Parser.expectedToken(Core.SEMICOLON);
        Parser.scanner.nextToken();
    }

    /**
     * this is the print method of the Call class.
     * @param indent - the number of tabs to indent the Call.
     */
    public void print(int indent) {
        // int j = 0;
        // while (j < indent) {
        //     System.out.print("\t");
        //     j++;
        // }
        // System.out.print("begin " + name + "(");
        // parameters.print(0);
        // parameters.print();
        // System.out.println(");");

        System.out.print("begin " + name + "(");
        params.print(0);
        System.out.println(");");
    }

    // /**
    //  * this is the execute method of the Call class.
    //  * @params - not currently available.
    //  */
    // public void execute() {
    //     // TODO: we need to implement the execute method...
    //     if (!Memory.subroutine.containsKey(name)) {
	// 		System.out.println("ERROR: attempting to call a procedure that has not been declared. procedure " + "\"" + name + "\"" + " is not declared.");
	// 		System.exit(-1); // exit with an error status...
	// 	}
    //     Function function = Memory.subroutine.get(name);
    //     Memory.pushScope(); // push to the call stack...
    //     List<String> formalParams = new ArrayList<>();
    //     Parameters head = function.parameters;
    //     while (head != null) {
    //         formalParams.add(head.name);
    //         head = head.parameters;
    //     } // formalParams should now contain all formal parameters...
    //     List<String> actualArgs = new ArrayList<>();
    //     head = parameters;
    //     while (head != null) {
    //         actualArgs.add(head.name);
    //         head = head.parameters;
    //     } // actualArgs should now contain all actual parameters...
    //     int j = 0;
    //     while (j < formalParams.size()) {
    //         String param = formalParams.get(j);
    //         String arg = actualArgs.get(j);
    //         Memory.link(param, arg);
    //         j++;
    //     }
    //     function.stmtSeq.execute();
    //     Memory.popScope(); // pop from the call stack...
    // }

    /**
     * this is the execute method of the Call class.
     * @params - not currently available.
     */
    public void execute() {
        if (!Memory.map.containsKey(name)) {
            System.out.println("ERROR: attempting to call a procedure that has not been declared. procedure " + "\"" + name + "\"" + " is not declared.");
            System.exit(-1); // exit with an error status...
        }
        Memory.pushFrameAndExecute(name, params);
        Memory.popFrame();
    }
}
