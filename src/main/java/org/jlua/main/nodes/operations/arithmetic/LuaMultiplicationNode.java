package org.jlua.main.nodes.operations.arithmetic;

import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.jlua.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public abstract class LuaMultiplicationNode extends LuaBinaryNode {

    @Specialization
    protected long multiply(long left, long right) {
        return ExactMath.multiplyExact(left, right);
    }

    @Specialization
    protected BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

}
