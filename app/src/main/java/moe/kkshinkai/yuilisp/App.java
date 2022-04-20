// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
