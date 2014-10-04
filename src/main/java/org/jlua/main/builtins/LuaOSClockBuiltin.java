package org.jlua.main.builtins;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.jlua.main.nodes.LuaNode;

/**
 * Created by Lucas Allan Amorim on 2014-09-14.
 */
@NodeInfo(shortName = "osclock")
public abstract class LuaOSClockBuiltin extends LuaNode {

    @Specialization
    public double osclock() {
        return System.nanoTime() / 1e9;
    }

}
