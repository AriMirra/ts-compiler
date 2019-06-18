package lexer.lexerStates;

import lexer.LexerInfo;
import lexer.tokens.TokenType;

public class ErrorState implements LexerState {

    public StateConsumeResult consume(char c, LexerInfo lexerInfo) {
        StateConsumeResult normalResult = new StateConsumeResult(
                ConsumeResultType.VALID,
                this,
                null,
                true);
        if (c == ';' || LexerUtil.isSpace(c))
            return new StateConsumeResult(
                    ConsumeResultType.ACCEPTED,
                    null,
                    lexerInfo.toToken(TokenType.UNKNOWN),
                    false);
        else {
            lexerInfo.addToString(c);
            return normalResult;
        }
    }
}
