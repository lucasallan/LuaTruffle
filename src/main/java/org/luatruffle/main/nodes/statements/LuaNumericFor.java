package org.luatruffle.main.nodes.statements;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.luaj.vm2.ast.Exp;
import org.luatruffle.main.nodes.LuaExpressionNode;
import org.luatruffle.main.nodes.LuaLongConstantNode;
import org.luatruffle.main.nodes.LuaNode;
import org.luatruffle.main.nodes.LuaStatementNode;
import org.luatruffle.main.nodes.expressions.LuaUnoExpression;
import org.luatruffle.main.nodes.operations.arithmetic.LuaAddNodeFactory;
import org.luatruffle.main.nodes.operations.arithmetic.LuaSubtractionNodeFactory;
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

    public LuaNumericFor(LuaExpressionNode init, LuaExpressionNode limit, LuaBlockNode blockNode, Object step) {
        this.init = init;
        this.limit = limit;
        this.blockNode = blockNode;
        this.step = step;
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
                    blockNode.executeVoid(frame);
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
