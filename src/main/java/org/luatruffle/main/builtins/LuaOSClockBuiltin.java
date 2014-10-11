package org.luatruffle.main.builtins;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.luatruffle.main.nodes.LuaNode;

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
