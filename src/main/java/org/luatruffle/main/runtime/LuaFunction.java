package org.luatruffle.main.runtime;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.utilities.CyclicAssumption;

/**
 * Created by Lucas Allan Amorim on 2014-09-10.
 */
public final class LuaFunction {

    private final String name;


    private RootCallTarget callTarget;

    private final CyclicAssumption callTargetStable;

    protected LuaFunction(String name) {
        this.name = name;
        this.callTargetStable = new CyclicAssumption(name);
    }

    public String getName() {
        return name;
    }

    protected void setCallTarget(RootCallTarget callTarget) {
        this.callTarget = callTarget;
        callTargetStable.invalidate();
    }

    public RootCallTarget getCallTarget() {
        return callTarget;
    }

    public Assumption getCallTargetStable() {
        return callTargetStable.getAssumption();
    }

    @Override
    public String toString() {
        return name;
    }

}
