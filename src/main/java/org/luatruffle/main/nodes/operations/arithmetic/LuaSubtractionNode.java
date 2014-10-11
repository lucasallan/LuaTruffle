package org.luatruffle.main.nodes.operations.arithmetic;

import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.luatruffle.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public abstract class LuaSubtractionNode extends LuaBinaryNode {

    @Specialization
    protected long subtract(long left, long right) {
        return ExactMath.subtractExact(left, right);
    }

    @Specialization
    protected double subtract(double left, double right) {
        return left - right;
    }

    @Specialization
    protected BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

}