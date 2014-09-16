package org.jlua.main.nodes.operations.relational;

import com.oracle.truffle.api.dsl.Specialization;
import org.jlua.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public abstract class LuaLessThanNode extends LuaBinaryNode {

    @Specialization
    protected boolean isLessThan(long left, long right) {
        return left < right;
    }

    @Specialization
    protected boolean isLessThan(BigInteger left, BigInteger right) {
        return left.compareTo(right) == -1;
    }

    @Specialization(guards = "isEitherString")
    protected boolean isLessThan(Object left, Object right) {
        return left.toString().length() < right.toString().length();
    }

}
