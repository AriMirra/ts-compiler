package parser.AST;

import java.util.Objects;

public class BinaryOpNode extends BinaryNode {
    private OperationType opType;

    public BinaryOpNode(int line, int column, AstNode left, AstNode right, OperationType opType) {
        super(AstNodeType.BINARYOP, line, column, left, right);
        this.opType = opType;
    }

    public BinaryOpNode(int line, int column, AstNode left, OperationType opType) {
        super(AstNodeType.BINARYOP, line, column, left);
        this.opType = opType;
    }

    public OperationType getOpType() {
        return opType;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + opType;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BinaryOpNode that = (BinaryOpNode) o;
        return opType == that.opType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), opType);
    }
}
