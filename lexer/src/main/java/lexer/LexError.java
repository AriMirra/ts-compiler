package lexer;

public class LexError {
    private String message;
    private Token token;

    private LexError(String message, Token token) {
        this.message = message;
        this.token = token;
    }

    public static LexError unexpectedToken(Token token) {
        String errorMessage = "unexpected token '" + token.getValue() + "'" + "at " + token.getLine() + ":" + token.getColumn();
        return new LexError(errorMessage, token);
    }
}
