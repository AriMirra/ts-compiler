package parser.AST;

public enum LanguageType {
    NUMBER,
    STRING;

    @Override
    public String toString() {
        return name();
    }
}
