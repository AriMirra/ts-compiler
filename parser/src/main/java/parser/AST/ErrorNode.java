package parser.AST;

public class ErrorNode extends AstNode {

    public ErrorNode(int line, int column) {
        super(AstNodeType.ERROR, line, column);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
