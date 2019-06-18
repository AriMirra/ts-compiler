package parser;


import lexer.tokens.Token;
import lexer.tokens.TokenType;

public class ParserError {
    private String message;
    private Token token;

    private ParserError(String message, Token token) {
        this.message = message;
        this.token = token;
    }

    static ParserError expected(Token token, TokenType type) {
        String message = "[Parser error]" +
                "\n\tToken Expected: " + type.toString() +
                "\n\tToken Received: " + token.toString();
        return new ParserError(message, token);
    }

    static ParserError withMessage(Token token, String errorMessage) {
        String message = "[Parser error]: " + errorMessage;
        return new ParserError(message, token);
    }

    public String getMessage() {
        return message;
    }

    public Token getToken() {
        return token;
    }

    public String toString() {
        return message;
    }
}
