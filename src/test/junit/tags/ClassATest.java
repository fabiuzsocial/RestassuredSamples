package junit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class ClassATest {

    @Test
    @Tag("development")
    @Tag("production")
    void testCaseA(TestInfo testInfo) {
        System.out.println("Test A");
    }
}
