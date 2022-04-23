package moe.kkshinkai.yuilisp.syntax;

public class StringLiteralSyntax implements SyntaxNode {
    private final String value;

    public StringLiteralSyntax(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
