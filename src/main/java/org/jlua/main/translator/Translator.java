package org.jlua.main.translator;

import org.jlua.main.nodes.LuaNode;
import org.luaj.vm2.ast.*;
import org.luaj.vm2.ast.Stat.LocalAssign;

import java.util.List;

/**
 * Created by Lucas Allan Amorim on 2014-09-08.
 */

public class Translator extends Visitor {

    @Override
    public void visit(Stat.Assign assign) {
        visitLuaNode(assign);

        for (Object ob : assign.vars){

            Exp.NameExp var = (Exp.NameExp) ob;
            var.accept(this);
            System.err.println("Var Assignment: " + var.name.name + " " + var.name.variable);

        }
    }

    @Override
    public void visit(Stat.Break aBreak) {
        visitLuaNode(aBreak);
    }

    @Override
    public void visit(Stat.FuncCallStat funcCallStat) {
        visitLuaNode(funcCallStat);
    }

    @Override
    public void visit(Stat.FuncDef funcDef) {
        System.err.println(funcDef.name.name.name);
        funcDef.body.accept(this);
        visitLuaNode(funcDef);
    }

    @Override
    public void visit(Stat.GenericFor genericFor) {
        visitLuaNode(genericFor);
    }

    @Override
    public void visit(Stat.IfThenElse ifThenElse) {
        ifThenElse.elseblock.accept(this);
        ifThenElse.ifblock.accept(this);
        ifThenElse.ifexp.accept(this);
        visitLuaNode(ifThenElse);
    }

    @Override
    public void visit(LocalAssign localAssign) {
        visitLuaNode(localAssign);
    }

    @Override
    public void visit(Stat.LocalFuncDef localFuncDef) {
        localFuncDef.accept(this);
        localFuncDef.body.accept(this);
        visitLuaNode(localFuncDef);
    }

    @Override
    public void visit(Stat.NumericFor numericFor) {
        numericFor.accept(this);
        numericFor.block.accept(this);
        numericFor.initial.accept(this);
        numericFor.step.accept(this);
        numericFor.limit.accept(this);
        visitLuaNode(numericFor);
    }

    @Override
    public void visit(Stat.RepeatUntil repeatUntil) {
        System.err.println("Visitor: " +repeatUntil.getClass().getName());
        visitLuaNode(repeatUntil);
    }

    @Override
    public void visit(Stat.Return aReturn) {
        for(Object ob : aReturn.values) {
            if (ob instanceof Exp){
                ((Exp) ob).accept(this);
            }
        }
        visitLuaNode(aReturn);
    }

    @Override
    public void visit(Stat.WhileDo whileDo) {
        visitLuaNode(whileDo);
    }

    @Override
    public void visit(FuncBody funcBody) {
        funcBody.block.accept(this);
        funcBody.parlist.accept(this);
     }

    @Override
    public void visit(FuncArgs funcArgs) {
        funcArgs.accept(this);
        for (Object ob : funcArgs.exps){
            handleUnknown(ob);
        }
    }

    @Override
    public void visit(TableField tableField) {
        tableField.accept(this);
        tableField.index.accept(this);
        tableField.rhs.accept(this);
    }

    @Override
    public void visit(Exp.AnonFuncDef anonFuncDef) {
        visitLuaNode(anonFuncDef);
    }

    @Override
    public void visit(Exp.BinopExp binopExp) {
        binopExp.lhs.accept(this);
        binopExp.rhs.accept(this);
        visitLuaNode(binopExp);
    }

    @Override
    public void visit(Exp.Constant constant) {

        if (constant.value.typename().equals("number")){
            System.err.println("Constant value: " +constant.value.checkint());
        } else {
            System.err.println("Constant value: " +constant.value.toString());
        }
        visitLuaNode(constant);
    }

    @Override
    public void visit(Exp.FieldExp fieldExp) {
        visitLuaNode(fieldExp);
    }

    @Override
    public void visit(Exp.FuncCall funcCall) {
        visitLuaNode(funcCall);
    }

    @Override
    public void visit(Exp.IndexExp indexExp) {
        visitLuaNode(indexExp);
    }

    @Override
    public void visit(Exp.MethodCall methodCall) {
        visitLuaNode(methodCall);
    }

    @Override
    public void visit(Exp.NameExp nameExp) {
        visitLuaNode(nameExp);
    }

    @Override
    public void visit(Exp.ParensExp parensExp) {
        visitLuaNode(parensExp);
    }

    @Override
    public void visit(Exp.UnopExp unopExp) {
        visitLuaNode(unopExp);
    }

    @Override
    public void visit(Exp.VarargsExp varargsExp) {
        visitLuaNode(varargsExp);
    }

    @Override
    public void visit(ParList parList) {
        for (Object ob : parList.names){
            handleUnknown(ob);
        }
    }

    @Override
    public void visit(TableConstructor tableConstructor) {
        visitLuaNode(tableConstructor);
    }

    @Override
    public void visitVars(List list) {
        System.err.println("List size: " + list.size());
    }


    @Override
    public void visit(Name name) {
        System.out.println("Visit Name: " + name.name);
    }

    @Override
    public void visit(String s) {
        System.out.println("String Name: " + s);
    }

    @Override
    public void visit(NameScope nameScope) {
        visitLuaNode(nameScope);
    }

    @Override
    public void visit(Stat.Goto aGoto) {
        visitLuaNode(aGoto);
    }

    @Override
    public void visit(Stat.Label label) {
        visitLuaNode(label);
    }

    private void visitLuaNode(Stat node) {
        System.err.println("Visitor: " +node.getClass().getName() + " Line: " + node.beginLine);
    }

    private void visitLuaNode(Exp node){
        System.err.println("Visitor: " +node.getClass().getName() + " Line: " + node.beginLine);
    }

    private void visitLuaNode(NameScope node){
        if (node != null) {
            System.err.println("Visitor: " + node.getClass().getName());
        }
    }

    private void visitLuaNode(Chunk node) {
        node.accept(this);
    }

    private void handleUnknown(Object ob) {
        if (ob instanceof Exp){
            ((Exp) ob).accept(this);
        } else if (ob instanceof Stat) {
            ((Stat) ob).accept(this);
        } else if (ob instanceof Name) {
            visit((Name) ob);
        } else {
            System.out.println("FuncArgs - Unknown type: " +ob.getClass().getName());
        }
    }
//    private void visitLuaNode(Object object) {
//        System.err.println("Visitor: " +object.getClass().getName());
//    }

}
