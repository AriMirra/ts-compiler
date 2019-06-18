package lexer.LexerStates;

public enum ConsumeResultType {
    VALID,
    TRANSITION,
    ACCEPTED_TRANSITION,
    ERROR,
    ERROR_TRANSITION,
    NEW_LINE
}

