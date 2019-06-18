package types;

import parser.AST.LanguageType;

public class NumberType implements Type {
    private Double value;

    public NumberType() {
        this.value = 0.0;
    }

    public NumberType(Double value) {
        this.value = value;
    }

    @Override
    public LanguageType type() {
        return LanguageType.NUMBER;
    }

    @Override
    public Type plus(Type type) {
        if (type.type() == LanguageType.NUMBER)
            return new NumberType(value + type.asDouble());
        else {
            return new StringType(asString() + type.asString());
        }
    }

    @Override
    public Type minus(Type type) {
        return new NumberType(value - type.asDouble());
    }

    @Override
    public Type times(Type type) {
        return new NumberType(value * type.asDouble());
    }

    @Override
    public Type divided(Type type) {
        return new NumberType(value / type.asDouble());
    }

    @Override
    public String asString() {
        return value.toString().substring(0,4);
    }

    @Override
    public Double asDouble() {
        return value;
    }
}
