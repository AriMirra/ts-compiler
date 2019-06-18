package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens;
    private List<LexError> errors;

    LexResult lex(String code) {
        return new LexResult(new ArrayList<>(), new ArrayList<>());
    }
}
