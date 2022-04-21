package moe.kkshinkai.yuilisp.syntax;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Lexer lexer;

    public Parser(String source) {
        this.lexer = new Lexer(source);
    }

    public Syntax getSyntax() {
        List<Syntax> list = new ArrayList<>();
        list.add(new IdentifierSyntax("define"));
        list.add(new PendingSequenceSyntax(new IdentifierSyntax("main")));

        while (lexer.nextToken() != TokenKind.EOF) {
            switch (lexer.getTokenKind()) {
                case L_PAREN -> list.add(parseSequence());
                case R_PAREN -> System.out.println("error: unexpected ')'");
                default -> list.add(parseExpression());
            }
        }

        return new PendingSequenceSyntax(list);
    }

    private Syntax parseSequence() {
        List<Syntax> list = new ArrayList<>();
        while (lexer.nextToken() != TokenKind.EOF && lexer.getTokenKind() != TokenKind.R_PAREN) {
            if (lexer.getTokenKind() == TokenKind.L_PAREN) {
                list.add(parseSequence());
            } else {
                list.add(parseExpression());
            }
        }
        return new PendingSequenceSyntax(list);
    }

    private Syntax parseExpression() {
        return switch (lexer.getTokenKind()) {
            case IDENTIFIER -> new IdentifierSyntax(lexer.getText());
            case SYMBOL_LITERAL -> new SymbolLiteralSyntax(lexer.getText().substring(1));
            case STRING_LITERAL -> new StringLiteralSyntax(lexer.getText().substring(1, lexer.getText().length() - 1));
            case INTEGER_LITERAL -> new IntegerLiteralSyntax(Integer.parseInt(lexer.getText()));
            case FLOAT_LITERAL -> new FloatLiteralSyntax(Float.parseFloat(lexer.getText()));
            case TRUE_LITERAL -> new BooleanLiteralSyntax(true);
            case FALSE_LITERAL -> new BooleanLiteralSyntax(false);
            case NIL_LITERAL -> new NilLiteralSyntax();
            default -> throw new IllegalStateException("Unexpected value: " + lexer.getTokenKind());
        };
    }
}
