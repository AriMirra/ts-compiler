package parser;

import lexer.tokens.TokenType;
import parser.AST.LanguageType;
import parser.AST.OperationType;

class ParserUtil {

    static boolean isLanguageType(TokenType type) {
        return type == TokenType.STRING || type == TokenType.NUMBER;
    }

    static boolean isLiteral(TokenType type) {
        return type == TokenType.STRING_LITERAL || type == TokenType.NUMBER_LITERAL;
    }

    static boolean isOperation(TokenType type) {
        return type == TokenType.PLUS || type == TokenType.MINUS || type == TokenType.TIMES || type == TokenType.SLASH;
    }

    static boolean isAdditive(OperationType type) {
        return type == OperationType.ADDITION || type == OperationType.SUBTRACTION;
    }

    static boolean isMultiplicative(OperationType type) {
        return type == OperationType.MULTIPLICATION || type == OperationType.DIVISION;
    }

    static OperationType toOpType(TokenType type) {
        switch (type) {
            case PLUS: return OperationType.ADDITION;
            case MINUS: return OperationType.SUBTRACTION;
            case SLASH: return OperationType.DIVISION;
            default: return OperationType.MULTIPLICATION;
        }
    }

    static LanguageType toLanguageType(TokenType type) {
        if (type == TokenType.STRING || type == TokenType.STRING_LITERAL)
            return LanguageType.STRING;
        else return LanguageType.NUMBER;
    }
}
