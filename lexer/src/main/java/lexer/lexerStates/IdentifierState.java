package lexer.lexerStates;

import lexer.LexerInfo;
import lexer.tokens.TokenType;

public class IdentifierState implements LexerState {

    public StateConsumeResult consume(char c, LexerInfo lexerInfo) {
        StateConsumeResult normalResult = new StateConsumeResult(
                ConsumeResultType.VALID,
                this,
                null,
                true);
        if(LexerUtil.isNumber(c) || LexerUtil.isLetter(c)) {
            lexerInfo.addToString(c);
            return normalResult;
        } else {
            if(lexerInfo.getTokenTable().containsKey(lexerInfo.getString())) {
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        null,
                        lexerInfo.toToken(lexerInfo.getTokenTable().get(lexerInfo.getString())),
                        false);
            } else {
                lexerInfo.getTokenTable().put(lexerInfo.getString(), TokenType.IDENTIFIER);
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        null,
                        lexerInfo.toToken(TokenType.IDENTIFIER),
                        false);
            }
        }
    }
}
