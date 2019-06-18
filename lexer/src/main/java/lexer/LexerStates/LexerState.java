package lexer.LexerStates;

public interface LexerState {
    StateConsumeResult consume(char c);
}
