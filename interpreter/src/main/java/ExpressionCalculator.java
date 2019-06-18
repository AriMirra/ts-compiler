import parser.AST.*;
import types.NumberType;
import types.StringType;
import types.Type;

import java.util.Map;

public class ExpressionCalculator implements Visitor {
    private Type result = null;
    private Map<String, Type> globalMemory;

    ExpressionCalculator(Map<String, Type> globalMemory) {
        this.globalMemory = globalMemory;
    }

    Type calculate(AstNode root) {
        root.accept(this);
        return result;
    }

    @Override
    public void visit(BinaryOpNode node) {
        node.getLeft().accept(this);
        Type left = result;
        node.getRight().accept(this);
        switch (node.getOpType()) {
            case ADDITION:
                result = left.plus(result);
                break;
            case SUBTRACTION:
                result = left.minus(result);
                break;
            case MULTIPLICATION:
                result = left.times(result);
                break;
            case DIVISION:
                result = left.divided(result);
                break;
        }
    }

    @Override
    public void visit(VariableNode node) {
        Type variable = globalMemory.get(node.getName());
        if (variable != null) result = variable;
        else throw new RuntimeException("Variable '" + node.getName() + "' has not been declared!");
    }

    @Override
    public void visit(ConstantNode node) {
        result = constantToType(node.getLanguageType(), node.getValue());
    }

    @Override
    public void visit(SequenceNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(BinaryNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(TypeNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(PrintNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void visit(ErrorNode node) {
        throw new UnsupportedOperationException();
    }

    private static Type constantToType(LanguageType languageType, String value) {
        if (languageType == LanguageType.NUMBER) return new NumberType(Double.parseDouble(value));
        else return new StringType(value);
    }
}
