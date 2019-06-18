package parser.AST;

import java.util.Objects;

public class VariableNode extends AstNode {
    private String name;

    public VariableNode(int line, int column, String name) {
        super(AstNodeType.VARIABLE, line, column);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + name;
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
        VariableNode that = (VariableNode) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
