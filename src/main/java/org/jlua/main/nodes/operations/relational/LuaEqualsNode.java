package org.jlua.main.nodes.operations.relational;

import com.oracle.truffle.api.ExactMath;
import com.oracle.truffle.api.dsl.Specialization;
import org.jlua.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public abstract class LuaEqualsNode extends LuaBinaryNode {

    @Specialization
    protected boolean isEquals(long left, long right) {
        return left == right;
    }

    @Specialization
    protected boolean isEquals(BigInteger left, BigInteger right) {
        return left.equals(right);
    }

    @Specialization(guards = "isEitherString")
    protected boolean isEquals(Object left, Object right) {
        return left.toString().equals(right.toString());
    }
}
