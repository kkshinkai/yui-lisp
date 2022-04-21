// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

public class FloatLiteralSyntax extends LiteralSyntax {
    private final double value;

    public FloatLiteralSyntax(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
