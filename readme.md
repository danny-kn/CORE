# The CORE Language Interpreter

A comprehensive interpreter implementation for the CORE programming language, featuring a complete lexical analyzer, syntax analyzer, and interpreter with memory management capabilities.

## Overview

The CORE Language Interpreter is a Java-based implementation that processes and executes programs written in the CORE programming language. The interpreter consists of multiple phases:

1. **Lexical Analysis (Scanner)** - Tokenizes the input source code.
2. **Syntax Analysis (Parser)** - Builds an abstract syntax tree and validates syntax.
3. **Interpretation** - Executes the parsed program with full memory management.

## The CORE Grammar

```
<procedure> ::= procedure ID is <decl-seq> begin <stmt-seq> end | procedure ID is begin <stmt-seq> end
<decl-seq> ::= <decl > | <decl><decl-seq> | <function> | <function><decl-seq>
<stmt-seq> ::= <stmt> | <stmt><stmt-seq>
<decl> ::= <decl-integer> | <decl-obj>
<decl-integer> ::= integer id ;
<decl-obj> ::= object id ;
<function> ::= procedure ID ( <parameters> ) is <stmt-seq> end
<parameters> ::= ID | ID , <parameters>
<stmt> ::= <assign> | <if> | <loop> | <out> | <decl> | <call>
<call> ::= begin ID ( <parameters> ) ;
<assign> ::= id = <expr> ; | id [ id ] = <expr> ; | id = new object( id, <expr> ); | id : id ;
<out> ::= print ( <expr> ) ;
<if> ::= if <cond> then <stmt-seq> end | if <cond> then <stmt-seq> else <stmt-seq> end
<loop> ::= while <cond> do <stmt-seq> end
<cond> ::= <cmpr> | not <cond> | [ <cond> ] | <cmpr> or <cond> | <cmpr> and <cond>
<cmpr> ::= <expr> == <expr> | <expr> < <expr>
<expr> ::= <term> | <term> + <expr> | <term> – <expr>
<term> ::= <factor> | <factor> * <term> | <factor> / <term>
<factor> ::= id | id [ id ] | const | ( <expr> ) | read ( )
```

## Usage

### Compilation
```bash
cd src
javac *.java
```

### Execution

**For programs without input data:**
```bash
java Main <program_file>
```

**For programs with input data:**
```bash
java Main <program_file> <data_file>
```

### Example Programs

**Simple Program (no input):**
```core
procedure main is
    integer x;
begin
    x = 10;
    print(x);
end
```

**Program with Input:**
```core
procedure main is
    integer x;
begin
    read(x);
    print(x + 5);
end
```
