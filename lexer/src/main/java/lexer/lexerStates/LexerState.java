package lexer.lexerStates;

import lexer.LexerInfo;

public interface LexerState {
    StateConsumeResult consume(char c, LexerInfo lexerInfo);
}
