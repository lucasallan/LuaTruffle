package org.jlua.main.nodes;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.jlua.main.runtime.LuaFunction;
import org.jlua.main.runtime.LuaNull;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
@TypeSystemReference(LuaTypes.class)
public abstract class LuaNode extends Node {

    public abstract Object execute(VirtualFrame frame);

    public void executeVoid(VirtualFrame frame) {
        execute(frame);
    }

    public double executeDouble(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectDouble(execute(frame));
    }

    public boolean executeBoolean(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectBoolean(execute(frame));
    }

    public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectLong(execute(frame));
    }

    public BigInteger executeBigInteger(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectBigInteger(execute(frame));
    }

    public String executeString(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectString(execute(frame));
    }

    public LuaNull executeLuaNull(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectLuaNull(execute(frame));
    }

    public LuaFunction executeLuaMethod(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectLuaFunction(execute(frame));
    }
}
