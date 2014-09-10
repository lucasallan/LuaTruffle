package org.jlua.main.nodes;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import org.jlua.main.nodes.LuaTypesGen;

import java.math.BigInteger;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
@TypeSystemReference(LuaTypes.class)
public abstract class LuaNode extends Node {

    public abstract Object execute(VirtualFrame frame);

    public boolean executeBoolean(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectBoolean(execute(frame));
    }

    public BigInteger executeBigInteger(VirtualFrame frame) throws UnexpectedResultException {
        return LuaTypesGen.LUATYPES.expectBigInteger(execute(frame));
    }
}
