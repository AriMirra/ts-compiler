package lexer.lexerStates;

import lexer.LexerInfo;
import lexer.tokens.TokenType;

public class NumberState implements LexerState {

    public StateConsumeResult consume(char c, LexerInfo lexerInfo) {
        StateConsumeResult normalResult = new StateConsumeResult(
                ConsumeResultType.VALID,
                this,
                null,
                true);
        if(LexerUtil.isNumber(c)) {
            lexerInfo.addToString(c);
            return normalResult;
        } else if (LexerUtil.isSpace(c) || LexerUtil.isOperator(c) || LexerUtil.isSeparator(c)) {
            return new StateConsumeResult(
                    ConsumeResultType.ACCEPTED,
                    this,
                    lexerInfo.toToken(TokenType.NUMBER_LITERAL),
                    false);
        } else {
            lexerInfo.addToString(c);
            return new StateConsumeResult(
                    ConsumeResultType.ERROR,
                    this,
                    lexerInfo.toToken(TokenType.UNKNOWN),
                    true);
        }
    }
}
