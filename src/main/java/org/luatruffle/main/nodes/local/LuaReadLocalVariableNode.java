package org.luatruffle.main.nodes.local;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.luatruffle.main.nodes.LuaExpressionNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class LuaReadLocalVariableNode extends LuaExpressionNode {

    protected abstract FrameSlot getSlot();

    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected long readLong(VirtualFrame frame) throws FrameSlotTypeException {
        return frame.getLong(getSlot());
    }

    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected boolean readBoolean(VirtualFrame frame) throws FrameSlotTypeException {
        return frame.getBoolean(getSlot());
    }

    @Specialization(rewriteOn = FrameSlotTypeException.class)
    protected Object readObject(VirtualFrame frame) throws FrameSlotTypeException {
        return frame.getObject(getSlot());
    }

    @Specialization(contains = {"readLong", "readBoolean", "readObject"})
    protected Object read(VirtualFrame frame) {
        return frame.getValue(getSlot());
    }
}
