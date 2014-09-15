package org.jlua.main.nodes.operations.relational;

import com.oracle.truffle.api.dsl.Specialization;
import org.jlua.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public abstract class LuaGreaterOrEqualsNode extends LuaBinaryNode {

    @Specialization
    protected boolean isGreaterOrEquals(long left, long right) {
        return left >= right;
    }

    @Specialization
    protected boolean isGreaterOrEquals(BigInteger left, BigInteger right) {
        return left.compareTo(right) == 1 || left.compareTo(right) == 0;
    }

    @Specialization(guards = "isString")
    protected boolean isGreaterOrEquals(Object left, Object right) {
        return left.toString().length() >= right.toString().length();
    }

    protected boolean isString(Object a, Object b) {
        return a instanceof String || b instanceof String;
    }
}