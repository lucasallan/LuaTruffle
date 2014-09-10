package org.jlua.main.nodes.statements;

import org.jlua.main.nodes.LuaExpressionNode;
import org.jlua.main.nodes.LuaStatementNode;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.source.SourceSection;
import com.oracle.truffle.api.utilities.BranchProfile;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
@NodeInfo(shortName = "if", description = "The node implementing a condional statement")
public class LuaIfNode extends LuaStatementNode {

	@Child
	private LuaExpressionNode conditionNode;
	@Child
	private LuaStatementNode thenPartNode;
	@Child
	private LuaStatementNode elsePartNode;

	private final BranchProfile thenProfile = new BranchProfile();
	private final BranchProfile elseProfile = new BranchProfile();

	@CompilerDirectives.CompilationFinal
	private int thenCount;
	@CompilerDirectives.CompilationFinal
	private int elseCount;

	public LuaIfNode(SourceSection src, LuaExpressionNode conditionNode,
			LuaStatementNode thenPartNode, LuaStatementNode elsePartNode) {
		this.conditionNode = conditionNode;
		this.thenPartNode = thenPartNode;
		this.elsePartNode = elsePartNode;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		if (CompilerDirectives.injectBranchProbability(getBranchProbability(),
				conditionNode.executeBoolean(frame))) {
			if (CompilerDirectives.inInterpreter()) {
				thenCount++;
			}
			thenProfile.enter();
			thenPartNode.executeVoid(frame);
		} else {
			if (CompilerDirectives.inInterpreter()) {
				elseCount++;
			}
			elseProfile.enter();
			elsePartNode.executeVoid(frame);
		}

	}

	private double getBranchProbability() {
		final int totalCount = thenCount + elseCount;

		if (totalCount == 0) {
			return 0;
		} else {
			return (double) thenCount / (double) (thenCount + elseCount);
		}
	}

}
