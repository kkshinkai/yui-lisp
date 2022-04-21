// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp;

import moe.kkshinkai.yuilisp.syntax.Parser;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        var parser = new Parser("(define x (+ 1 2)) ('asda \"asdasd\") 2123.123e+2 asd 123 nil true");
        var syntax = parser.getSyntax();

        System.out.println(syntax);
    }
}
