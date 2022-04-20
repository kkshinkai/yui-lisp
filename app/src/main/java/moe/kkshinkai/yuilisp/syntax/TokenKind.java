// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

public enum TokenKind {
    IDENTIFIER,
    SYMBOL_LITERAL,
    STRING_LITERAL,
    INTEGER_LITERAL,
    FLOAT_LITERAL,
    TRUE_LITERAL,
    FALSE_LITERAL,
    NIL_LITERAL,
    EOF,

    L_PAREN,
    R_PAREN,
}
