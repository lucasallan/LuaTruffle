package org.jlua.main.nodes.local;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.jlua.main.nodes.LuaExpressionNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-15.
 */
@NodeChild("valueNode")
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class LuaWriteLocalVariableNode extends LuaExpressionNode {

    protected abstract FrameSlot getSlot();

    @Specialization(guards = "isLongKind")
    protected long writeLong(VirtualFrame frame, long value) {
        frame.setLong(getSlot(), value);
        return value;
    }

    @Specialization(guards = "isBooleanKind")
    protected boolean writeBoolean(VirtualFrame frame, boolean value) {
        frame.setBoolean(getSlot(), value);
        return value;
    }

    protected boolean isLongKind() {
        return isKind(FrameSlotKind.Long);
    }

    protected boolean isBooleanKind() {
        return isKind(FrameSlotKind.Boolean);
    }

    private boolean isKind(FrameSlotKind kind) {
        if (getSlot().getKind() == kind) {
            return true;
        } else if (getSlot().getKind() == FrameSlotKind.Illegal) {
            CompilerDirectives.transferToInterpreterAndInvalidate();
            getSlot().setKind(kind);
            return true;
        } else {
            return false;
        }
    }
}
