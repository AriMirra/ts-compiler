package parser;

import parser.AST.AstNode;

import java.util.List;

public class ParseResult {
    private boolean error;
    private List<ParserError> errors;
    private AstNode ast;

    ParseResult(boolean error, List<ParserError> errors, AstNode ast) {
        this.error = error;
        this.errors = errors;
        this.ast = ast;
    }

    public boolean isError() {
        return error;
    }

    public List<ParserError> getErrors() {
        return errors;
    }

    public AstNode getAst() {
        return ast;
    }
}
