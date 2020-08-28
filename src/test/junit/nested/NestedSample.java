package junit.nested;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedSample {

    @Test
    public void testextmethod1() {

        System.out.println("test ext method 1");

    }

    @Nested
    class TestNest {

        @BeforeEach
        public void init() {
            System.out.println("Init");
        }

        @Test
        public void testmethod1() {
            System.out.println("This is nested method 1");
        }

        @Test
        public void testmethod2() {
            System.out.println("This is nested method 2");
        }

        @Test
        public void testmethod3() {
            System.out.println("This is nested method 3");
        }

    }

    @Test
    public void testextmethod2() {

        System.out.println("test ext method 2");

    }

    @Nested
    class TestNest2 {

        @BeforeEach
        public void init() {
            System.out.println("Init");
        }

        @Test
        public void testmethod1() {
            System.out.println("This is nested 2 method 1");
        }

        @Test
        public void testmethod2() {
            System.out.println("This is nested 2 method 2");
        }

        @Test
        public void testmethod3() {
            System.out.println("This is nested 2 method 3");
        }

    }
}

