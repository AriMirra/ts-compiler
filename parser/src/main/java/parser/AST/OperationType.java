package parser.AST;

public enum OperationType {
    ADDITION,
    SUBTRACT,
    MULTIPLICATION,
    DIVISION;

    public static boolean isAdditive(OperationType type) {
        return type == ADDITION || type == SUBTRACT;
    }

    public static boolean isMultiplicative(OperationType type) {
        return type == MULTIPLICATION || type == DIVISION;
    }

    @Override
    public String toString() {
        return name();
    }
}
