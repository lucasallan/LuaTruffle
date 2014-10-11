package org.luatruffle.main.nodes.operations.arithmetic;

import com.oracle.truffle.api.dsl.Specialization;
import org.luatruffle.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public abstract class LuaExponentiationNode extends LuaBinaryNode {

    @Specialization
    protected long pow(long left, long right) {
        return (long) Math.pow(left, right);
    }

    @Specialization
    protected BigInteger pow(BigInteger left, BigInteger right) {
        return left.pow(right.intValue());
    }

}