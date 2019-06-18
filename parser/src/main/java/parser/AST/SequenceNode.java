package parser.AST;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SequenceNode extends AstNode {
    private List<AstNode> children;

    public SequenceNode(int line, int column) {
        super(AstNodeType.SEQUENCE, line, column);
        this.children = new ArrayList<>();
    }

    public List<AstNode> getChildren() {
        return children;
    }

    public void add(AstNode node) {
        children.add(node);
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
        SequenceNode that = (SequenceNode) o;
        return Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), children);
    }
}
