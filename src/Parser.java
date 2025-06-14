import java.util.*;

/**
 * this is the Parser class.
 */
public class Parser {
	public static Scanner scanner;

	/**
	 * ...
	 * @param expected - the expected token.
	 */
	static void expectedToken(Core expected) {
		if (scanner.currentToken() != expected) {
			System.out.println("ERROR: expected " + expected + ", received " + scanner.currentToken() + ".");
			System.exit(-1); // exit with an error status...
		}
	}

}
