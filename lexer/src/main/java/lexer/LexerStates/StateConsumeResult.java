package lexer.LexerStates;

import lexer.Token;

public class StateConsumeResult {
    private ConsumeResultType type;
    private LexerState nextState;
    private Token token;
    private boolean consumed;

    public StateConsumeResult(ConsumeResultType type, LexerState nextState, Token token, boolean consumed) {
        this.type = type;
        this.nextState = nextState;
        this.token = token;
        this.consumed = consumed;
    }

    public ConsumeResultType getType() {
        return type;
    }

    public void setType(ConsumeResultType type) {
        this.type = type;
    }

    public LexerState getNextState() {
        return nextState;
    }

    public void setNextState(LexerState nextState) {
        this.nextState = nextState;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean isConsumed() {
        return consumed;
    }
}
