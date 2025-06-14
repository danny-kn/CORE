import java.util.*;

/**
 * this is the Memory class.
 */
public class Memory {
	public static Scanner data;
	public static int gc = 0; // the number of reachable objects.

	// represents a variable.
	public static class Variable {
		Core type;
		int intVal;
		// String defaultKey;
		// Map<String, Integer> mapVal;
		Object objRef;
		Variable(Core t) {
			this.type = t;
		}
	}

	public static HashMap<String, Variable> global;
	public static Stack<Stack<HashMap<String, Variable>>> local;
	public static HashMap<String, Function> subroutine = new HashMap<String, Function>();

	public static HashMap<String, Function> map;

	// represents an object.
	public static class Object {
		int refCount; // the number of references to the object.
		String defaultKey;
		Map<String, Integer> mapVal;
		Object(String key, int value) {
			this.refCount = 0;
			this.defaultKey = key;
			this.mapVal = new HashMap<>();
			this.mapVal.put(key, value);
		}
	}

	// these are the helper methods to manage memory.

	// init. and handle global memory structs.
	public static void initializeGlobal() {
		global = new HashMap<String, Variable>();
		map = new HashMap<String, Function>();
	}

	// clear and handle global memory structs.
	public static void clearGlobal() {
		ArrayList<Variable> vars = new ArrayList<Variable>(global.values());
		int j = 0;
		while (j < vars.size()) {
			decRefCount(vars.get(j).objRef);
			j++;
		}
		global = null;
		map = null;
	}

	// init. and handle local memory structs.
	public static void initializeLocal() {
		local = new Stack<Stack<HashMap<String, Variable>>>();
		local.push(new Stack<HashMap<String, Variable>>());
		local.peek().push(new HashMap<String, Variable>());
	}

	/**
	 * this method pushes a "scope".
	 * @param - not currently available (n/a).
	 * @return - not currently available (n/a).
	 */
	public static void pushScope() {
		local.peek().push(new HashMap<String, Variable>());
	}

	/**
	 * this method pops a "scope".
	 * @param - not currently available (n/a).
	 * @return - not currently available (n/a).
	 */
	public static void popScope() {
		ArrayList<Variable> vars = new ArrayList<Variable>(local.peek().pop().values());
		int j = 0;
		while (j < vars.size()) {
			decRefCount(vars.get(j).objRef);
			j++;
		}
	}

	// this method handles the decl integer.
	public static void declareInteger(String id) {
		Variable var = new Variable(Core.INTEGER);
		if (local != null) {
			local.peek().peek().put(id, var);
		} else {
			global.put(id, var);
		}
	}

	// this method handles the decl object.
	public static void declareObject(String id) {
		Variable var = new Variable(Core.OBJECT);
		if (local != null) {
			local.peek().peek().put(id, var);
		} else {
			global.put(id, var);
		}
	}

	// this method retrieves a value from memory.
	public static int load(String id) {
		Variable var = getLocalOrGlobal(id);
		if (var.type == Core.INTEGER) {
			return var.intVal;
		} else {
			return var.objRef.mapVal.get(var.objRef.defaultKey);
		}
	}

	// this method retrieves a value for the key.
	public static int load(String id, String key) {
		Variable var = getLocalOrGlobal(id);
		if (!var.objRef.mapVal.containsKey(key)) {
			System.exit(-1); // exit with an error status...
		}
		return var.objRef.mapVal.get(key);
	}

	// this method stores a value (e.g., integer or object) at the default key.
	public static void store(String id, int value) {
		// Variable var = getLocalOrGlobal(id);
		if (getLocalOrGlobal(id).type == Core.INTEGER) {
			getLocalOrGlobal(id).intVal = value;
		} else {
			getLocalOrGlobal(id).objRef.mapVal.put(getLocalOrGlobal(id).objRef.defaultKey, value);
		}
	}

	// this method stores a value at a key.
	public static void store(String id, String key, int value) {
		// Variable var = getLocalOrGlobal(id);
		getLocalOrGlobal(id).objRef.mapVal.put(key, value);
	}

	// this method handles "new object" assignment.
	public static void allocate(String id, String key, int value) {
		// Variable var = getLocalOrGlobal(id);
		decRefCount(getLocalOrGlobal(id).objRef);

		// var.mapVal = new HashMap<>();
		// var.defaultKey = key;
		// var.mapVal.put(var.defaultKey, value);

		Object obj = new Object(key, value);
		obj.refCount = 1;
		getLocalOrGlobal(id).objRef = obj;
		gc++;
		System.out.println("gc:" + gc);
	}

	// this method handles "id : id" assignment.
	public static void alias(String lhs, String rhs) {
		Variable var1 = getLocalOrGlobal(lhs);
		Variable var2 = getLocalOrGlobal(rhs);
		decRefCount(var1.objRef);

		// var1.mapVal = var2.mapVal;
		// var1.defaultKey = var2.defaultKey;

		var1.objRef = var2.objRef;
		incRefCount(var1.objRef);
	}

	// public static void link(String formalParam, String actualArg) {
	// 	Variable param = new Variable(Core.OBJECT);
	// 	Variable arg = getLocalOrGlobal(actualArg);
	// 	param.mapVal = arg.mapVal;
	// 	param.defaultKey = arg.defaultKey;
	// 	// please note that i modified the following line of code:
	// 	local.peek().peek().put(formalParam, param);
	// }

	private static Variable getLocalOrGlobal(String id) {
		Variable res = global.get(id);
		if (local.peek().size() > 0) {
			HashMap<String, Variable> temp = local.peek().pop();
			res = getLocalOrGlobal(id);
			local.peek().push(temp);
			if (local.peek().peek().containsKey(id)) {
				res = local.peek().peek().get(id);
			}
		}
		return res;
	}

	/**
	 * this is the pushFrameAndExecute method of the Memory class.
	 * @param name - the name of the function to execute.
	 * @param args - the arguments to pass to the function.
	 * @return - not currently available (n/a).
	 */
	// please note that i imported the following method from "BlamelessProject4" with minor modifications
	// to handle the implementation of the garbage collector (i.e., gc).
	public static void pushFrameAndExecute(String name, Parameters args) {
		Function func = map.get(name);
		ArrayList<String> formals = func.params.execute();
		ArrayList<String> arguments = args.execute();
		Stack<HashMap<String, Variable>> frame = new Stack<HashMap<String, Variable>>();
		frame.push(new HashMap<String, Variable>());
		int j = 0;
		while (j < arguments.size()) {
			Variable var1 = getLocalOrGlobal(arguments.get(j));
			Variable var2 = new Variable(Core.OBJECT);
			var2.objRef = var1.objRef;
			incRefCount(var2.objRef);
			frame.peek().put(formals.get(j), var2);
			j++;
		}
		local.push(frame);
		func.stmtSeq.execute();
	}

	// this method checks if the reference count of an object is zero.
	public static void isZero(Object obj)
	{
		if (obj.refCount == 0)
		{
			gc--;
			System.out.println("gc:" + gc);
		}
	}

	/**
	 * this is the popFrame method of the Memory class.
	 * @param - not currently available (n/a).
	 * @return - not currently available (n/a).
	 */
	// please note that i imported the following method from "BlamelessProject4" with minor modifications
	// to handle the implementation of the garbage collector (i.e., gc).
	public static void popFrame() {
		ArrayList<Variable> vars = new ArrayList<Variable>(local.pop().pop().values());
		int j = 0;
		while (j < vars.size()) {
			decRefCount(vars.get(j).objRef);
			j++;
		}
	}

	/**
	 * this method increments the reference count of an object.
	 * @param obj - the object for which to increment the reference count.
	 * @return - not currently available (n/a).
	 */
	public static void incRefCount(Object obj) {
		if (obj != null) { ++obj.refCount; }
	}

	/**
	 * this method decrements the reference count of an object.
	 * @param obj - the object for which to decrement the reference count.
	 * @return - not currently available (n/a).
	 */
	public static void decRefCount(Object obj) {
		if (obj != null) {
			--obj.refCount;
			isZero(obj);
		}
	}

}
