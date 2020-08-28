package junit.parametrized;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

public class TestMath {
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 5, 6, 8, 10, 100, 1000}) //ValueSource: Specifies a hardcoded list of integers or Strings.
    void testIsEven(int number) {
        Assertions.assertTrue(MathTools.isEven(number));
    }


    @ParameterizedTest
    @MethodSource("generateEvenNumbers") //MethodSource: Invokes a static method that generates a stream or collection of items.
    void testIsEvenRange(int number) {
        Assertions.assertTrue(MathTools.isEven(number));
    }

    static IntStream generateEvenNumbers() {
        return IntStream.iterate(0, i -> i + 2).limit(500);
    }
}
