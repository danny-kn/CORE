/* please note the following: */
/* <stmt> ::= <assign> | <if> | <loop> | <print> | <read> | <decl> | <call> */

import java.util.*;

/**
 * this is the Stmt interface.
 */
public interface Stmt {
	public void parse();
	public void print(int indent);
	public void execute();
}
