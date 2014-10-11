package org.luatruffle.main.nodes.operations.arithmetic;

import com.oracle.truffle.api.dsl.Specialization;
import org.luatruffle.main.nodes.LuaBinaryNode;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
public abstract class LuaDivisionOperation extends LuaBinaryNode {

    @Specialization
    protected long divide(long left, long right) {
        return left / right;
    }

    @Specialization
    protected BigInteger divide(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

}
