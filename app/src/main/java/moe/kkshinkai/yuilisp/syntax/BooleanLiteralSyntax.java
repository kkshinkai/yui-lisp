// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

public class BooleanLiteralSyntax extends LiteralSyntax {
    private final boolean value;

    public BooleanLiteralSyntax(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
