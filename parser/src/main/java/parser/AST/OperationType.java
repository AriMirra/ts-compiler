package parser.AST;

public enum OperationType {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION;

    public static boolean isAdditive(OperationType type) {
        return type == ADDITION || type == SUBTRACTION;
    }

    public static boolean isMultiplicative(OperationType type) {
        return type == MULTIPLICATION || type == DIVISION;
    }

    @Override
    public String toString() {
        return name();
    }
}
