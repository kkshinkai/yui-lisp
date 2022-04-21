// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp.syntax;

import java.util.List;

public class PendingSequenceSyntax implements Syntax {
    private List<Syntax> elements;

    public PendingSequenceSyntax(List<Syntax> elements) {
        this.elements = elements;
    }
}
