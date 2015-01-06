package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.luatruffle.main.nodes.LuaExpressionNode;
import org.luatruffle.main.nodes.LuaLongConstantNode;
import org.luatruffle.main.nodes.LuaNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.expressions.LuaUnoExpression;
import org.luatruffle.main.nodes.local.LuaWriteLocalVariableNodeFactory;
import org.luatruffle.main.nodes.statements.controlflow.LuaBreakException;

/**
 * Created by Lucas Allan Amorim on 15-01-05.
 */
@NodeInfo(shortName = "numericFor")
public class LuaNumericFor extends LuaStatementNode {

    @Child
    private LuaExpressionNode init;
    @Child
    private LuaExpressionNode limit;
    @Child
    private LuaBlockNode blockNode;
    @Child
    private Object step;
    @Child
    private String varName;

    public LuaNumericFor(LuaExpressionNode init, LuaExpressionNode limit, LuaBlockNode blockNode, Object step, String varName) {
        this.init = init;
        this.limit = limit;
        this.blockNode = blockNode;
        this.step = step;
        this.varName = varName;
    }

    @Override
    public void executeVoid(VirtualFrame frame) {
        try {
            Long initial = (Long) init.execute(frame);  // 50
            Long limitFinal = limit.executeLong(frame); // 1
            if (step instanceof LuaUnoExpression && ((LuaUnoExpression) step).getOp() == 19) {

                LuaExpressionNode rhs = ((LuaUnoExpression) step).getRhs();
                Long number = rhs.executeLong(frame);

                while (initial >= limitFinal) {
                    final LuaNode[] block = new LuaNode[2];
                    block[0] = LuaWriteLocalVariableNodeFactory.create(init, frame.getFrameDescriptor().findOrAddFrameSlot(varName));
                    block[1] = blockNode;
                    new LuaBlockNode(block).executeVoid(frame);

                    initial = initial - number;
                }

                throw LuaBreakException.SINGLETON;
            } else if (step instanceof LuaLongConstantNode) {

                Long number = ((LuaLongConstantNode) step).executeLong(frame);
                while (initial <= limitFinal) {
                    blockNode.executeVoid(frame);
                    initial = initial + number;
                }
            }
        }
        catch (LuaBreakException ex) {
            // TODO do I need to do something here?
        } catch (UnexpectedResultException e) {
            e.printStackTrace();
        }
    }
}
