package parser;

import lexer.tokens.Token;
import org.junit.Test;
import parser.AST.*;

import java.util.ArrayList;
import java.util.List;

import static lexer.tokens.TokenType.*;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void parse() {
        // let str1: string = test1;
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token(LET, "let", 1, 1));
        tokens.add(new Token(IDENTIFIER, "str1", 1, 5));
        tokens.add(new Token(COLON, ":", 1, 9));
        tokens.add(new Token(STRING, "string", 1, 11));
        tokens.add(new Token(EQL, "=", 1, 18));
        tokens.add(new Token(STRING_LITERAL, "test1", 1, 20));
        tokens.add(new Token(SEMICOLON, ";", 1, 27));

        VariableNode str1 = new VariableNode(1, 5, "str1");
        TypeNode string = new TypeNode(1, 11, LanguageType.STRING);
        BinaryNode declaration = new BinaryNode(AstNodeType.DECLARATION, 1, 1, str1, string);
        ConstantNode test1 = new ConstantNode(1, 20, LanguageType.STRING, "test1");
        BinaryNode assign = new BinaryNode(AstNodeType.ASSIGN, 1, 18, declaration, test1);

        SequenceNode ast = new SequenceNode(0, 0);
        ast.getChildren().add(assign);

        Parser parser = new Parser();
        ParseResult parseResult = parser.parse(tokens.iterator());

        assertFalse(parseResult.isError());
        assertEquals(parseResult.getAst(), ast);
    }
}
