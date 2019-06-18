package lexer;

import lexer.tokens.Token;

import java.util.List;

public class LexResult {
    private List<Token> tokens;
    private List<LexError> errors;
    private boolean error;

    LexResult(List<Token> tokens, List<LexError> errors, boolean error) {
        this.tokens = tokens;
        this.errors = errors;
        this.error = error;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<LexError> getErrors() {
        return errors;
    }

    public boolean isError() {
        return error;
    }
}
