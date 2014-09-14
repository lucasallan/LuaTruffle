package org.jlua.main.nodes.expressions;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.nodes.LuaNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-11.
 */
public class LuaBinaryExpression extends LuaExpressionNode {

    protected LuaNode leftSide;
    protected LuaNode rightSide;
    protected int operator;

    public LuaBinaryExpression(LuaNode leftSide, LuaNode rightSide, int operator) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.operator = operator;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return executeBoolean(frame);
    }

    public boolean executeBoolean(VirtualFrame frame){
        Object left = leftSide.execute(frame);
        Object right = rightSide.execute(frame);

        if (operator == 24) {
            return left != right;
        } else if (operator == 61) {
            return left == right;
        }

        if (!(left instanceof Long) || !(right instanceof Long)) {
            System.err.println("needs be properly handled");
            // Strings needs to be checked in alphabetical order
            return false;
        }

        Long leftLong = (Long) leftSide.execute(frame);
        Long rightLong = (Long) rightSide.execute(frame);

        if (operator == 63) {
            return leftLong > rightLong;
        } else if (operator == 62) {
            return leftLong >= rightLong;
        } else if (operator == 26) {
            return leftLong <= rightLong;
        } else if (operator == 25) {
            return leftLong < rightLong;
        }
        return false;
    }
}
