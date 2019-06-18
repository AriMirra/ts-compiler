package lexer.lexerStates;

import lexer.LexerInfo;
import lexer.tokens.Token;
import lexer.tokens.TokenType;

public class DefaultState implements LexerState {

    public StateConsumeResult consume(char c, LexerInfo lexerInfo) {
        lexerInfo.nextString();

        if (LexerUtil.isLetter(c)) {
            lexerInfo.addToString(c);
            return new StateConsumeResult(ConsumeResultType.TRANSITION, new IdentifierState(), Token.empty(), true);

        } else if (LexerUtil.isNumber(c)) {
            lexerInfo.addToString(c);
            return new StateConsumeResult(ConsumeResultType.TRANSITION, new NumberState(), Token.empty(),true);

        } else if (c == '\n') {
            return new StateConsumeResult(ConsumeResultType.NEW_LINE, this, Token.empty(),true);

        } else if (LexerUtil.isSpace(c)) {
            return new StateConsumeResult(ConsumeResultType.VALID, this, Token.empty(), true);

        } else if (LexerUtil.isOperator(c)){
            return new StateConsumeResult(ConsumeResultType.TRANSITION, new OperatorState(), Token.empty(), false);

        } else if (LexerUtil.isStringLimiter(c)) {
            return new StateConsumeResult(ConsumeResultType.TRANSITION, new StringState(), Token.empty(), false);
        } else {
            lexerInfo.addToString(c);
            StateConsumeResult result = new StateConsumeResult(ConsumeResultType.ACCEPTED, this, Token.empty(), true);
            switch (c) {
                case '(' : {
                    result.setToken(lexerInfo.toToken(TokenType.LPAREN));
                }
                case ')' : {
                    result.setToken(lexerInfo.toToken(TokenType.RPAREN));
                }
                case ':' : {
                    result.setToken(lexerInfo.toToken(TokenType.COLON));
                }
                case ';' : {
                    result.setToken(lexerInfo.toToken(TokenType.SEMICOLON));
                }
                default: {
                    result.setType(ConsumeResultType.ERROR);
                    result.setToken(lexerInfo.toToken(TokenType.UNKNOWN));
                }
            }
            return result;
        }
    }
}
