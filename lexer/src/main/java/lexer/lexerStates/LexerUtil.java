package lexer.lexerStates;

import lexer.tokens.TokenType;

import java.util.HashMap;
import java.util.Map;

class LexerUtil {

    static Map<String, TokenType> createTokenTable() {
        Map<String, TokenType> tokenTable = new HashMap<>();
        tokenTable.put(TokenType.LET.getName(), TokenType.LET);
        tokenTable.put(TokenType.PRINT.getName(), TokenType.PRINT);
        tokenTable.put(TokenType.STRING.getName(), TokenType.STRING);
        tokenTable.put(TokenType.NUMBER.getName(), TokenType.NUMBER);
        return tokenTable;
    }

    static boolean isSeparator(char c) {
        return c == '(' || c == ')' || c == ';';
    }

    static boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\f' || c == '\n';
    }

    static boolean isStringLimiter(char c) {
        return c == '"' || c == '\'';
    }

    static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=';
    }
}
