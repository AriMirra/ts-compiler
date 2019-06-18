package lexer;

import java.util.List;

public class LexResult {
    private List<Token> tokens;
    private List<LexError> errors;

    LexResult(List<Token> tokens, List<LexError> errors) {
        this.tokens = tokens;
        this.errors = errors;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public List<LexError> getErrors() {
        return errors;
    }
}
