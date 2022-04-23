// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

import java.util.List;

public class PendingSequenceSyntax implements SyntaxNode {
    private List<SyntaxNode> elements;

    public PendingSequenceSyntax(List<SyntaxNode> elements) {
        this.elements = elements;
    }

    public PendingSequenceSyntax(SyntaxNode... elements) {
        this.elements = List.of(elements);
    }
}
