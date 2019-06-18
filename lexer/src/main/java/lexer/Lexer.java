package lexer;

import lexer.lexerStates.DefaultState;
import lexer.lexerStates.ErrorState;
import lexer.lexerStates.LexerState;
import lexer.lexerStates.StateConsumeResult;
import lexer.tokens.Token;
import lexer.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lexer {
    private LexerInfo lexerInfo;
    private LexerState currentState = new DefaultState();
    private List<Token> tokens = new ArrayList<>();
    private List<LexError> errors = new ArrayList<>();
    private int index = 0;
    private StateConsumeResult result;

    public Lexer(Map<String, TokenType> tokenTable) {
        this.lexerInfo = new LexerInfo(tokenTable);
    }

    LexResult lex(String code) {
        result = null;
        for (index = 0; index < code.length(); incrementIndex()) {
            char c = code.charAt(index);
            result = currentState.consume(c, lexerInfo);
            lexByType();
        }
        return new LexResult(tokens, errors, errors.isEmpty());
    }

    private void lexByType() {
        switch (result.getType()) {
            case ACCEPTED:
                Token token = result.getToken();
                if (token.getType() == TokenType.UNKNOWN) errors.add(LexError.unexpectedToken(token));
                else tokens.add(token);
                currentState = new DefaultState();
                break;
            case TRANSITION:
                currentState = result.getNextState();
                break;
            case NEW_LINE:
                lexerInfo.nextLine();
                break;
            case ERROR:
                currentState = new ErrorState();
                break;
        }
    }

    private void incrementIndex() {
        if (result.isConsumed()) {
            lexerInfo.nextColumn();
            index++;
        }
    }
}
