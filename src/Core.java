import java.util.*;

/* please note that this enumeration represents our tokens. */
public enum Core {
	/* these are the keywords of the language. */
	AND,
	BEGIN,
	CASE,
	DO,
	ELSE,
	END,
	FOR,
	IF,
	IN,
	INTEGER,
	IS,
	NEW,
	NOT,
	OBJECT,
	OR,
	PRINT,
	PROCEDURE,
	READ,
	RETURN,
	THEN,

	/* these are the symbols of the language. */
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	ASSIGN,
	EQUAL,
	LESS,
	COLON,
	SEMICOLON,
	PERIOD,
	COMMA,
	LPAREN,
	RPAREN,
	LSQUARE,
	RSQUARE,
	LCURL,
	RCURL,

	/* these are the special tokens of the language. */
	CONST,
	ID,
	STRING,
	EOS,
	ERROR
}
