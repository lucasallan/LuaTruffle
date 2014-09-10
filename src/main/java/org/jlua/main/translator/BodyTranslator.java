package org.jlua.main.translator;

import org.jlua.main.nodes.LuaNode;
import org.luaj.vm2.ast.Block;
import org.luaj.vm2.ast.Exp;
import org.luaj.vm2.ast.Stat;
import org.luaj.vm2.ast.Stat.LocalAssign;
import org.luaj.vm2.ast.Visitor;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */

public class BodyTranslator extends Visitor {

    public Object visitLocalAssign(LocalAssign localAssign) {

        return null;
    }

}
