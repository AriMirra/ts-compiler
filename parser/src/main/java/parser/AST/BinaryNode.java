package parser.AST;

import java.util.Objects;

public class BinaryNode extends AstNode {
    private AstNode left;
    private AstNode right;

    public BinaryNode(AstNodeType type, int line, int column, AstNode left, AstNode right) {
        super(type, line, column);
        this.left = left;
        this.right = right;
    }

    BinaryNode(AstNodeType type, int line, int column, AstNode left) {
        super(type, line, column);
        this.left = left;
    }

    public BinaryNode(AstNodeType type, int line, int column) {
        super(type, line, column);
    }

    public AstNode getLeft() {
        return left;
    }

    public void setLeft(AstNode left) {
        this.left = left;
    }

    public AstNode getRight() {
        return right;
    }

    public void setRight(AstNode right) {
        this.right = right;
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
        BinaryNode that = (BinaryNode) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), left, right);
    }
}
