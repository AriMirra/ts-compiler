package parser.AST;

import java.util.Objects;

public class ConstantNode extends AstNode {
    private LanguageType languageType;
    private String value;

    public ConstantNode(int line, int column, LanguageType languageType, String value) {
        super(AstNodeType.CONSTANT, line, column);
        this.languageType = languageType;
        this.value = value;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + "\n<" +  languageType + ":" + value + ">";
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
        ConstantNode that = (ConstantNode) o;
        return languageType == that.languageType &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), languageType, value);
    }
}
