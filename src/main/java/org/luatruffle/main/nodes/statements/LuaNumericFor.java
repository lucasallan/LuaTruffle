package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.statements.controlflow.LuaBreakException;

import java.util.ArrayList;

/**
 * Created by Lucas Allan Amorim on 15-01-05.
 */
@NodeInfo(shortName = "numericFor")
public class LuaNumericFor extends LuaStatementNode {

    @Child
    private LuaBlockNode blockNodes;

    public LuaNumericFor(LuaBlockNode blockNodes) {
        this.blockNodes = blockNodes;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        blockNodes.executeVoid(frame);
        throw LuaBreakException.SINGLETON;
//            } else if (step instanceof LuaLongConstantNode) {
//
//                Long number = ((LuaLongConstantNode) step).executeLong(frame);
//                while (initial <= limitFinal) {
//                    blockNode.executeVoid(frame);
//                    initial = initial + number;
//                }
//            }


    }
}
