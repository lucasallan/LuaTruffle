package org.luatruffle.main.translator;

import org.junit.Test;

/**
 * Created by Lucas Allan Amorim on 2014-09-25.
 */
public class MethodDefinitionTest extends BaseTranslatorTest {
    @Test
    public void testFunctionDefinitionAndCallTest(){
        assertEquals(createCallTarget(createTempFile(
                "function newFunction()\n" +
                    "return 10\n" +
                "end\n" +
                "return newFunction()")
        ).call(), (long) 10);
    }
//    @Test
//    public void testFunctionsWithParamTest(){
//        assertEquals(createCallTarget(createTempFile(
//                        "function return_value(a)\n" +
//                                "    return a\n" +
//                                "end\n" +
//                                "return return_value(10)")
//        ).call(), (long) 10);
//    }
}
