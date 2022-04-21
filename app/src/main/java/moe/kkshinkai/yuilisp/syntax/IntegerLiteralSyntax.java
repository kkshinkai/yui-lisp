// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

public class IntegerLiteralSyntax extends LiteralSyntax {
    private final int value;

    public IntegerLiteralSyntax(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
