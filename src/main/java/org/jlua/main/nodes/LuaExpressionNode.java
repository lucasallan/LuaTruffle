package org.jlua.main.nodes;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.jlua.main.nodes.LuaTypesGen;
import org.jlua.main.runtime.LuaNull;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
public abstract class LuaExpressionNode extends LuaNode {

	public void executeVoid(VirtualFrame frame) {
		execute(frame);
	}

    public abstract Object executeGeneric(VirtualFrame frame);

    public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectLong(executeGeneric(frame));
    }

    public BigInteger executeBigInteger(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectBigInteger(executeGeneric(frame));
    }

    public boolean executeBoolean(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectBoolean(executeGeneric(frame));
    }

    public String executeString(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectString(executeGeneric(frame));
    }

    public LuaNode executeLuaNode(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectLuaNode(executeGeneric(frame));
    }

    public LuaNull executeLuaNull(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectLuaNull(executeGeneric(frame));
    }
}
