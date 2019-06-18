package parser.AST;

public interface  Visitable {
    void accept(Visitor visitor);
}
