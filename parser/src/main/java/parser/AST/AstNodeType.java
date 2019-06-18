package parser.AST;

public enum AstNodeType {
    VARIABLE,
    ASSIGN,
    DECLARATION,
    CONSTANT,
    SEQUENCE,
    BINARYOP,
    TYPE,
    PRINT,
    ERROR;

    public String toString() {
        return name();
    }
}
