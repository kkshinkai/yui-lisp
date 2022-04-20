// Copyright (c) Kk Shinkai and Yui. All Rights Reserved. See LICENSE.txt in the
// project root for license information.

package moe.kkshinkai.yuilisp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
