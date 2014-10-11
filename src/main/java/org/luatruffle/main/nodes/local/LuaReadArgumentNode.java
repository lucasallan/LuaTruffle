package org.luatruffle.main.nodes.local;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.luatruffle.main.nodes.LuaExpressionNode;
import org.luatruffle.main.runtime.LuaNull;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
@NodeField(name = "slot", type = FrameSlot.class)
public class LuaReadArgumentNode extends LuaExpressionNode {

    private final int index;

    public LuaReadArgumentNode(int index) {
        this.index = index;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Object[] args = frame.getArguments();
        if (index < args.length) {
            return args[index];
        } else {
            return LuaNull.SINGLETON;
        }
    }

}
