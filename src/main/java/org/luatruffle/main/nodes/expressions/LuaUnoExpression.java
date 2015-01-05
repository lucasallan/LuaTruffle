package org.luatruffle.main.nodes.expressions;

import org.luatruffle.main.nodes.LuaExpressionNode;

/**
 * Created by Lucas Allan Amorim on 15-01-05.
 */
public final class LuaUnoExpression  {

    private int op;
    private LuaExpressionNode rhs;

    public LuaUnoExpression(int op, LuaExpressionNode rhs) {
        this.op = op;
        this.rhs = rhs;
    }

    public int getOp() {
        return op;
    }

    public LuaExpressionNode getRhs() {
        return rhs;
    }
}
