package moe.kkshinkai.yuilisp.syntax;

interface SyntaxVisitor<Return> {
    Return visit(Syntax syntax);
    Return visit(PendingSequenceSyntax syntax);
    Return visit(BooleanLiteralSyntax syntax);
    Return visit(FloatLiteralSyntax syntax);
    Return visit(IntegerLiteralSyntax syntax);
    Return visit(StringLiteralSyntax syntax);
    Return visit(SymbolLiteralSyntax syntax);
    Return visit(IdentifierSyntax syntax);
    Return visit(NilLiteralSyntax syntax);
}
