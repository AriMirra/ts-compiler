package lexer.lexerStates;

import lexer.LexerInfo;
import lexer.tokens.TokenType;

public class StringState implements LexerState{
    private char limiter;
    private boolean done = true;

    @Override
    public StateConsumeResult consume(char c, LexerInfo lexerInfo) {
        StateConsumeResult normalResult = new StateConsumeResult(
                ConsumeResultType.VALID,
                this,
                null,
                true);
        if (done) {
            if(LexerUtil.isStringLimiter(c)) {
                done = false;
                limiter = c;
                return normalResult;
            } else {
                return new StateConsumeResult(ConsumeResultType.ERROR, this, lexerInfo.toToken(TokenType.UNKNOWN), true);
            }
        } else if (c == limiter) {
            return new StateConsumeResult(ConsumeResultType.ACCEPTED, this, lexerInfo.toToken(TokenType.STRING_LITERAL), true);
        } else {
            lexerInfo.addToString(c);
            return normalResult;
        }
    }
}
