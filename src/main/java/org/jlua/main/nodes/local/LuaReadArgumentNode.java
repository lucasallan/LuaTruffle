package org.jlua.main.nodes.local;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.runtime.LuaNull;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
public class LuaReadArgumentNode extends LuaExpressionNode {

    private final int index;

    public LuaReadArgumentNode(int index) {
        this.index = index;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (index < args.length) {
            return args[index];
        } else {
            return LuaNull.SINGLETON;
        }
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return executeGeneric(frame);
    }
}
