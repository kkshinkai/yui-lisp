package moe.kkshinkai.yuilisp.syntax;

public class IdentifierSyntax extends LiteralSyntax {
    private final String value;

    public IdentifierSyntax(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}