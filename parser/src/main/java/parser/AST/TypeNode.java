package parser.AST;

import java.util.Objects;

public class TypeNode extends AstNode {
    private LanguageType languageType;

    public TypeNode(int line, int column, LanguageType languageType) {
        super(AstNodeType.TYPE, line, column);
        this.languageType = languageType;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + languageType;
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
        TypeNode that = (TypeNode) o;
        return languageType == that.languageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), languageType);
    }
}
