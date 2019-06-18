package types;

import parser.AST.LanguageType;

public interface Type {
    LanguageType type();
    Type plus(Type type);
    Type minus(Type type);
    Type times(Type type);
    Type divided(Type type);
    String asString();
    Double asDouble();
}
