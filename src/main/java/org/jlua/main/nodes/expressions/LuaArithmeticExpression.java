package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaConstantNode;
import org.jlua.main.nodes.LuaNode;
import sun.tools.java.Type;
import sun.tools.tree.BinaryExpression;
import sun.tools.tree.Expression;

import java.math.BigInteger;

/**
 * Created by lucas on 2014-09-14.
 */
public class LuaArithmeticExpression extends LuaBinaryExpression {

    public LuaArithmeticExpression(LuaNode leftSide, LuaNode rightSide, int operator) {
        super(leftSide, rightSide, operator);
    }

    public Object execute(VirtualFrame frame) {
        Object left = leftSide.execute(frame);
        Object right = rightSide.execute(frame);

        // TODO use @specialization
        if (operator == 13) {
            return addOperation(left, right);
        }
        throw new UnsupportedOperationException();
    }

    private Object addOperation(Object left, Object right) {


        if (left instanceof BigInteger && right instanceof BigInteger) {
            return ((BigInteger) left).add((BigInteger) right);
        }
        if (left instanceof Integer && right instanceof Integer) {
            return (Integer) left + (Integer) right;
        }
        throw new UnsupportedOperationException();
    }
}
