package parser.AST;

import java.util.Objects;

public class PrintNode extends AstNode {
    private AstNode child;

    public PrintNode(int line, int column) {
        super(AstNodeType.PRINT, line, column);
    }

    public AstNode getChild() {
        return child;
    }

    public void setChild(AstNode child) {
        this.child = child;
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
        PrintNode printNode = (PrintNode) o;
        return Objects.equals(child, printNode.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), child);
    }
}
