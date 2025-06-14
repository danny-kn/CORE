/* please note the following: */
/* <parameters> ::= id | id, <parameters> */

import java.util.*;

/**
 * this is the Parameters class.
 */
public class Parameters {
    String name;
    Parameters params;

    /**
     * this is the parse method of the Parameters class.
     * @params - not currently available.
     */
    public void parse() {
        Parser.expectedToken(Core.ID);
        name = Parser.scanner.getId();
        Parser.scanner.nextToken();
        if (Parser.scanner.currentToken() == Core.COMMA) {
            Parser.scanner.nextToken(); // consume the comma token...
            params = new Parameters();
            params.parse();
        }
    }

    /**
     * this is the print method of the Parameters class.
     * @param indent - the number of tabs to indent the Parameters.
     */
    public void print(int indent) {
        // System.out.print(name);
        // if (parameters != null) {
        //     System.out.print(", ");
        //     parameters.print(indent);
        // }
        System.out.print(name);
        if (params != null) {
            System.out.print(", ");
            params.print(0);
        }
    }

    // /**
    //  * this is the execute method of the Parameters class.
    //  * @params - not currently available.
    //  */
    // public void execute() {
    //     // TODO: we need to implement the execute method...
    //     List<String> paramNames = new ArrayList<>();
    //     paramNames.add(name);
    //     // https://www.geeksforgeeks.org/traversal-of-singly-linked-list/
    //     Parameters currParam = parameters;
    //     while (currParam != null) {
    //         paramNames.add(currParam.name);
    //         currParam = currParam.parameters;
    //     }
    //     // https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
    //     if (paramNames.size() != new HashSet<>(paramNames).size()) {
    //         System.out.println("ERROR: duplicate formal parameter name. parameter " + "\"" + paramNames.get(0) + "\"" + " is already defined.");
    //         System.exit(-1); // exit with an error status...
    //     }
    // }

    /**
     * this is the execute method of the Parameters class.
     * @params - not currently available.
     */
    public ArrayList<String> execute() {
        ArrayList<String> res;
        if (params == null) {
            res = new ArrayList<String>();
        } else {
            res = params.execute();
        }
        res.add(name);
        return res;
    }
}
