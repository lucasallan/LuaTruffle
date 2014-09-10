package org.jlua.main.nodes;

import java.math.BigInteger;

import com.oracle.truffle.api.dsl.*;
import com.oracle.truffle.api.frame.VirtualFrame;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */
@TypeSystem({ LuaNode.class, boolean.class, BigInteger.class, String.class})
public abstract class LuaTypes {


}
