package parser.AST;

import java.util.Objects;

public abstract class AstNode implements Visitable {
    private AstNodeType type;
    private int line;
    private int column;

    AstNode(AstNodeType type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }

    public AstNodeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + "\n(" + line + "," + column + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        AstNode astNode = (AstNode) o;
        return line == astNode.line &&
                column == astNode.column &&
                type == astNode.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, line, column);
    }
}
