import java.util.*;

/**
 * this is the main class of the program.
 */
public class Main {
	/**
	 * this is the main method of the program.
	 * @param args - the command line arguments.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(args[0]);
		Parser.scanner = scanner;
		Procedure procedure = new Procedure();
		procedure.parse();
		if (args.length > 1) {
			Memory.data = new Scanner(args[1]);
		}
		procedure.execute();
	}
}
