import parser.AST.*;
import types.NumberType;
import types.StringType;
import types.Type;

import java.util.HashMap;
import java.util.Map;

public class Interpreter implements Visitor {

    private Map<String, Type> globalMemory = new HashMap<>();
    private ExpressionCalculator calculator;

    private String variable;
    private LanguageType languageType;

    public Interpreter() {
        this.calculator = new ExpressionCalculator(globalMemory);
    }

    public void interpret(Visitable visitable) {
        visitable.accept(this);
    }

    @Override
    public void visit(SequenceNode node) {
        for (AstNode child : node.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visit(BinaryNode node) {
        node.getLeft().accept(this);

        if (node.getType() == AstNodeType.DECLARATION) {
            node.getRight().accept(this);
            if (globalMemory.containsKey(variable)) throw new RuntimeException("Variable '" + variable + "' already declared!");
            Type type = languageType == LanguageType.NUMBER ? new NumberType() : new StringType();
            globalMemory.put(variable, type);

        } else if (node.getType() == AstNodeType.ASSIGN) {
            Type resultType = calculator.calculate(node.getRight());
            globalMemory.put(variable, resultType);
        }
    }

    @Override
    public void visit(TypeNode node) {
        languageType = node.getLanguageType();
    }

    @Override
    public void visit(VariableNode node) {
        variable = node.getName();
    }

    @Override
    public void visit(PrintNode node) {
        System.out.println(calculator.calculate(node.getChild()).asString());
    }

    @Override
    public void visit(BinaryOpNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(ConstantNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(ErrorNode node) {
        throw new UnsupportedOperationException();
    }
}
