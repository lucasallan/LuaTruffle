package org.luatruffle.main.nodes.operations.arithmetic;

import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.luatruffle.main.nodes.LuaBinaryNode;
import org.luatruffle.main.nodes.LuaUnaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public abstract class LuaNegateNode extends LuaUnaryNode {

    @Specialization
    protected long negate(long value) {
        return -value;
    }

}
