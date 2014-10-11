package org.luatruffle.main.translator;

import org.junit.Test;
import org.luatruffle.main.translator.BaseTranslatorTest;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public class ArithmeticOperatorsTest extends BaseTranslatorTest {

    @Test
    public void testAddOperatorsWithNumbers(){
        long expectedResult = 4;
        assertEquals(createCallTarget(createTempFile("return 2 + 2")).call(), expectedResult);
    }

    @Test
    public void testAddOperatorsWithString(){
        assertEquals(createCallTarget(createTempFile("return 'Hello' + ' world'")).call(), "Hello world");
    }

    @Test
    public void testMultiplicationOperators(){
        long expectedValue = 8;
        assertEquals(createCallTarget(createTempFile("return 2 * 4")).call(), expectedValue);
    }

    @Test
    public void testDivisionOperators(){
        long expectedValue = 2;
        assertEquals(createCallTarget(createTempFile("return 4 / 2")).call(), expectedValue);
    }

    @Test
    public void testSubtractionOperators(){
        long expectedValue = 2;
        assertEquals(createCallTarget(createTempFile("return 4 - 2")).call(), expectedValue);
    }

    @Test
    public void testExponentiationOperators(){
        long expectedValue = 16;
        assertEquals(createCallTarget(createTempFile("return 4 ^ 2")).call(), expectedValue);
    }
}
