package lexer.LexerStates;

import lexer.Token;
import lexer.TokenType;

import java.util.Map;

public class LexerInfo {
    private int line = 1;
    private int column = 1;
    private int startLine = 1;
    private int startColumn = 1;
    private String currentString = "";
    private Map<String, TokenType> tokenTable;

    public LexerInfo(Map<String, TokenType> tokenTable) {
        this.tokenTable = tokenTable;
    }

    public Token toToken(TokenType type) {
        return new Token(type, currentString, startLine, startColumn);
    }

    public void nextLine() {
        line++;
        column = 1;
    }

    public void nextColumn() {
        column++;
    }

    public void nextString() {
        startLine = line;
        startColumn = column;
        currentString = "";
    }

    public String addToString(char c) {
        currentString += c;
        return currentString;
    }

    public String getString() {
        return currentString;
    }

    public Map<String, TokenType> getTokenTable() {
        return tokenTable;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }
}
