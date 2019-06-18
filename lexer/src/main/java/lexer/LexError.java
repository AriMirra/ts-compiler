package lexer;

import lexer.tokens.Token;

class LexError {
    private String message;
    private Token token;

    private LexError(String message, Token token) {
        this.message = message;
        this.token = token;
    }

    static LexError unexpectedToken(Token token) {
        String errorMessage = "unexpected token '" + token.getValue() + "'" + "at " + token.getLine() + ":" + token.getColumn();
        return new LexError(errorMessage, token);
    }

    @Override
    public String toString() {
        return "[ERROR]: " + token.toString();
    }
}
