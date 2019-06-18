package lexer.lexerStates;

import lexer.LexerInfo;
import lexer.tokens.TokenType;

public class OperatorState implements LexerState {

    @Override
    public StateConsumeResult consume(char c, LexerInfo lexerInfo) {
        lexerInfo.addToString(c);
        switch (c) {
            case '+':
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        this,
                        lexerInfo.toToken(TokenType.PLUS),
                        true
                );

            case '-':
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        this,
                        lexerInfo.toToken(TokenType.MINUS),
                        true
                );

            case '*':
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        this,
                        lexerInfo.toToken(TokenType.TIMES),
                        true
                );

            case '=':
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        this,
                        lexerInfo.toToken(TokenType.EQL),
                        true
                );

            case '/':
                return new StateConsumeResult(
                        ConsumeResultType.ACCEPTED,
                        this,
                        lexerInfo.toToken(TokenType.SLASH),
                        true
                );

            default:
                return new StateConsumeResult(
                        ConsumeResultType.ERROR,
                        this,
                        lexerInfo.toToken(TokenType.UNKNOWN),
                        false
                );
        }
    }
}
