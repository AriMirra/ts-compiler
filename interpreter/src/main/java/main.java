import lexer.Lexer;
import lexer.lexerStates.LexerUtil;
import lexer.tokens.Token;
import parser.AST.AstNode;
import parser.Parser;

import java.util.List;

public class main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer(LexerUtil.createTokenTable());
        Parser parser = new Parser();
        Interpreter interpreter = new Interpreter();

        try {
            System.out.println("tokens:");
            String code = "let pepe: number = 5 / 6;\n" +
                    "let pepito: number;\n" +
                    "pepito = pepe - 1;\n" +
                    "print(pepito + \"Holaaaa\");";
            List<Token> tokens = lexer.lex(code).getTokens();
//            tokens.forEach(t -> System.out.println(t.toString()));
            AstNode ast = parser.parse(tokens.iterator()).getAst();
            System.out.println("\nresult:");
            interpreter.interpret(ast);
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
        }
    }
}
