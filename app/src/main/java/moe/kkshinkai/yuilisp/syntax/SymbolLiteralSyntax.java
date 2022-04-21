// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

public class SymbolLiteralSyntax extends LiteralSyntax {
    private final String value;

    public SymbolLiteralSyntax(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
