package lexer.tokens;

public enum TokenType {
    UNKNOWN,
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    SLASH("/"),
    LPAREN("("),
    RPAREN(")"),
    SEMICOLON(";"),
    COMMA(","),
    PERIOD("."),
    EQL("="),
    COLON(":"),
    IDENTIFIER("identifier"),
    LET("let"),
    PRINT("print"),
    STRING("string"),
    NUMBER("number"),
    NUMBER_LITERAL("number literal"),
    STRING_LITERAL("string literal"),
    END;

    private String name;

    TokenType() {}

    TokenType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }
}
