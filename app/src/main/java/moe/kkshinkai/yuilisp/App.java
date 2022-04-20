// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp;

import moe.kkshinkai.yuilisp.syntax.Lexer;
import moe.kkshinkai.yuilisp.syntax.TokenKind;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        var lexer = new Lexer("(define x (+ 1 2)) ('asda \"asdasd\") 2123.123e+2 asd 123");

        while (lexer.nextToken() != TokenKind.EOF) {
            System.out.println(lexer.getTokenKind() + " " + lexer.getText());
        }
    }
}
