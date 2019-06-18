package lexer;

import lexer.lexerStates.LexerUtil;
import lexer.tokens.Token;
import lexer.tokens.TokenType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void lexString() {
        String code = "let str: string = \"string\";";

        List<Token> result = new ArrayList<>();
        result.add(new Token(TokenType.LET, "let", 1, 1));
        result.add(new Token(TokenType.IDENTIFIER, "str", 1, 5));
        result.add(new Token(TokenType.COLON, ":", 1, 8));
        result.add(new Token(TokenType.STRING, "string", 1, 10));
        result.add(new Token(TokenType.EQL, "=", 1, 17));
        result.add(new Token(TokenType.STRING_LITERAL, "string", 1, 19));
        result.add(new Token(TokenType.SEMICOLON, ";", 1, 27));

        Lexer lexer = new Lexer(LexerUtil.createTokenTable());
        LexResult lexResult = lexer.lex(code);
        System.out.println("tokens:");
        lexResult.getTokens().forEach(token -> System.out.println(token.toString()));
        System.out.println("---");
        System.out.println("errors:");
        lexResult.getErrors().forEach(error -> System.out.println(error.toString()));
        assertFalse(lexResult.isError());
        assertEquals(lexResult.getTokens().size(), result.size());
        assertTrue(lexResult.getTokens().containsAll(result));
    }

    @Test
    public void lexNumber() {
        String code = "let int: number = 2;";

        List<Token> result = new ArrayList<>();
        result.add(new Token(TokenType.LET, "let", 1, 1));
        result.add(new Token(TokenType.IDENTIFIER, "int", 1, 5));
        result.add(new Token(TokenType.COLON, ":", 1, 8));
        result.add(new Token(TokenType.NUMBER, "number", 1, 10));
        result.add(new Token(TokenType.EQL, "=", 1, 17));
        result.add(new Token(TokenType.NUMBER_LITERAL, "2", 1, 19));
        result.add(new Token(TokenType.SEMICOLON, ";", 1, 20));

        Lexer lexer = new Lexer(LexerUtil.createTokenTable());
        LexResult lexResult = lexer.lex(code);
        System.out.println("tokens:");
        lexResult.getTokens().forEach(token -> System.out.println(token.toString()));
        System.out.println("---");
        System.out.println("errors:");
        lexResult.getErrors().forEach(error -> System.out.println(error.toString()));
        assertFalse(lexResult.isError());
        assertEquals(lexResult.getTokens().size(), result.size());
        assertTrue(lexResult.getTokens().containsAll(result));
    }
}
