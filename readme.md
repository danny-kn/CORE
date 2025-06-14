# The CORE Interpreter

## The Grammar

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

