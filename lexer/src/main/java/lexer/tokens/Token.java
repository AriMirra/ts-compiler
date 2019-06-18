package lexer.tokens;

public class Token {
    private TokenType type;
    private String value;
    private int line;
    private int column;

    private Token() {}

    public Token(TokenType type, String value, int line, int column) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;

        Token token = (Token) object;
        return
                this.value.equals(token.value) &&
                this.type.equals(token.type) &&
                this.line == token.line &&
                this.column == token.column;
    }

    public String toString() {
        return type.toString() + ":" + value + " at " + line + ":" + column;
    }

    public static Token empty() {return new Token();}
}
