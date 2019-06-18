package types;

import parser.AST.LanguageType;

public class StringType implements Type {
    private String value;

    public StringType() {
        this.value = "";
    }

    public StringType(String value) {
        this.value = value;
    }

    @Override
    public LanguageType type() {
        return LanguageType.STRING;
    }

    @Override
    public Type plus(Type type) {
        return new StringType(value + type.asString());
    }

    @Override
    public Type minus(Type type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type times(Type type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type divided(Type type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public Double asDouble() {
        throw new UnsupportedOperationException();
    }
}
