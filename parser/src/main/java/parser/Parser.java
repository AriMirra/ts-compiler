package parser;

import lexer.tokens.Token;
import lexer.tokens.TokenType;
import parser.AST.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static parser.ParserUtil.*;

public class Parser {
    private Iterator<Token> tokens;
    private Token token;
    private ErrorNode lastError;
    private List<ParserError> errors;

    public Parser() {
    }

    public ParseResult parse(Iterator<Token> tokens) {
        this.tokens = tokens;
        this.errors = new ArrayList<>();
        SequenceNode root = new SequenceNode(0, 0);
        nextToken();

        while (token != null) {
            root.add(parseStatement());
        }

        return new ParseResult(!errors.isEmpty(), errors, root);
    }

    private AstNode parseStatement() {
        switch (token.getType()) {
            case LET:
                AstNode declarationNode = parseDeclaration();
                if (declarationNode.getType() == AstNodeType.ERROR) return declarationNode;
                switch (token.getType()) {
                    case EQL:
                        return parseAssign(declarationNode);
                    case SEMICOLON:
                        nextToken();
                        return declarationNode;
                    default:
                        return errorNode("Expected ';' or '='");
                }

            case IDENTIFIER:
                VariableNode variableNode = new VariableNode(token.getLine(), token.getColumn(), token.getValue());
                nextToken();
                if (token.getType() == TokenType.EQL) return parseAssign(variableNode);
                break;

            case PRINT:
                PrintNode printNode = new PrintNode(token.getLine(), token.getColumn());
                nextToken();
                if (differentToken(TokenType.LPAREN)) return lastError;
                nextToken();
                printNode.setChild(parseExpression());
                if (differentToken(TokenType.RPAREN)) return lastError;
                nextToken();
                if (differentToken(TokenType.SEMICOLON)) return lastError;
                nextToken();
                return printNode;
        }
        return errorNode("Not a statement");
    }

    private AstNode parseDeclaration() {
        BinaryNode declarationNode = new BinaryNode(AstNodeType.DECLARATION, token.getLine(), token.getColumn());

        nextToken();
        if (differentToken(TokenType.IDENTIFIER)) return lastError;

        declarationNode.setLeft(new VariableNode(token.getLine(), token.getColumn(), token.getValue()));

        nextToken();
        if (differentToken(TokenType.COLON)) return lastError;
        nextToken();

        if (isLanguageType(token.getType())) {
            declarationNode.setRight(new TypeNode(token.getLine(), token.getColumn(), toLanguageType(token.getType())));
            nextToken();
            return declarationNode;
        } else {
            return errorNode("Expected a TYPE token");
        }
    }

    private AstNode parseExpression() {
        BinaryOpNode opRoot = null;
        BinaryOpNode currentOp = null;
        AstNode currentValue = null;

        while (token.getType() != TokenType.RPAREN && token.getType() != TokenType.SEMICOLON) {

            if (isLiteral(token.getType())) {
                if (currentValue != null) return expressionErrorNode("Expected operation or end token");
                currentValue = new ConstantNode(token.getLine(), token.getColumn(),
                        toLanguageType(token.getType()), token.getValue());
                nextToken();
            } else if (token.getType() == TokenType.IDENTIFIER) {
                if (currentValue != null) return expressionErrorNode("Expected operation or end token");
                currentValue = new VariableNode(token.getLine(), token.getColumn(), token.getValue());
                nextToken();
            } else if (isOperation(token.getType())) {
                if (currentValue == null) return expressionErrorNode("Expected literal or identifier token");
                OperationType opType = toOpType(token.getType());

                // If root is null, initialize root with current value as left.
                if (opRoot == null) {
                    opRoot = new BinaryOpNode(token.getLine(), token.getColumn(), currentValue, opType);
                    currentOp = opRoot;
                }
                // If operation is additive or if root is multiplicative,
                // this operation is the new root with old root as left.
                else if (isAdditive(opType) || isMultiplicative(opRoot.getOpType())) {
                    currentOp.setRight(currentValue);
                    opRoot = new BinaryOpNode(token.getLine(), token.getColumn(), opRoot, opType);
                    currentOp = opRoot;
                }
                // If operation is multiplicative, and root is not:
                else {
                    // If father operation is multiplicative, this operation is roots right child, with previous child as left.
                    if (isMultiplicative(currentOp.getOpType())) {
                        currentOp.setRight(currentValue);
                        BinaryOpNode multNode = new BinaryOpNode(
                                token.getLine(), token.getColumn(), opRoot.getRight(), opType);
                        opRoot.setRight(multNode);
                        currentOp = multNode;

                        // If father operation is additive, this operation is now right child of father operation.
                    } else {
                        BinaryOpNode multNode = new BinaryOpNode(
                                token.getLine(), token.getColumn(), currentValue, opType);
                        currentOp.setRight(multNode);
                        currentOp = multNode;
                    }
                }
                // Value used.
                currentValue = null;
                nextToken();
            } else {
                return expressionErrorNode("Unexpected token");
            }
        }

        if (currentValue != null && currentOp != null) {
            currentOp.setRight(currentValue);
            return opRoot;
        } else if (currentValue == null) {
            this.errors.add(ParserError.withMessage(token, "Expected expression"));
            lastError = new ErrorNode(token.getLine(), token.getColumn());
            finishExpression();
            return lastError;
        }
        return currentValue;
    }

    private AstNode parseAssign(AstNode left) {
        if (differentToken(TokenType.EQL)) return lastError;
        Token assignToken = token;
        nextToken();

        AstNode result = new BinaryNode(AstNodeType.ASSIGN, assignToken.getLine(), assignToken.getColumn(), left, parseExpression());

        if (differentToken(TokenType.SEMICOLON)) return lastError;

        nextToken();

        return result;
    }

    private void nextToken() {
        if (tokens.hasNext()) token = tokens.next();
        else token = null;
    }

    private void finishStatement() {
        do nextToken();
        while (token != null && token.getType() != TokenType.SEMICOLON);
        nextToken();
    }

    private void finishExpression() {
        while (token != null && token.getType() != TokenType.SEMICOLON && token.getType() != TokenType.RPAREN)
            nextToken();
    }

    private ErrorNode errorNode(String message) {
        this.errors.add(ParserError.withMessage(token, message));
        lastError = new ErrorNode(token.getLine(), token.getColumn());
        finishStatement();
        return lastError;
    }

    private ErrorNode expressionErrorNode(String message) {
        errors.add(ParserError.withMessage(token, message));
        lastError = new ErrorNode(token.getLine(), token.getColumn());
        finishExpression();
        return lastError;
    }

    private boolean differentToken(TokenType type) {
        if (token.getType() == type) return false;
        errors.add(ParserError.expected(token, type));
        lastError = new ErrorNode(token.getLine(), token.getColumn());
        finishStatement();
        return true;
    }
}
