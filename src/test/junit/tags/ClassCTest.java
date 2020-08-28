package junit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class ClassCTest {

    @Test
    @Tag("development")
    void testCaseA(TestInfo testInfo) {
        System.out.println("Test C");
    }
}
