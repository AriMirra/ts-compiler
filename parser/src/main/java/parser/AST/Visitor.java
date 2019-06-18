package parser.AST;

interface Visitor {
    void visit(SequenceNode node);
    void visit(BinaryNode node);
    void visit(BinaryOpNode node);
    void visit(TypeNode node);
    void visit(VariableNode node);
    void visit(PrintNode node);
    void visit(ConstantNode node);
    void visit(ErrorNode node);
}
