package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<em>
 *<li>Rule 21:  optFormalArgList ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 22:  optFormalArgList ::= ($ formalArgList )$
 *</b>
 */
public class optFormalArgList extends ASTNode implements IoptFormalArgList
{
    private formalArgList _formalArgList;

    public formalArgList getformalArgList() { return _formalArgList; }

    public optFormalArgList(IToken leftIToken, IToken rightIToken,
                            formalArgList _formalArgList)
    {
        super(leftIToken, rightIToken);

        this._formalArgList = _formalArgList;
        ((ASTNode) _formalArgList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_formalArgList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        //
        // The super call test is not required for now because an Ast node
        // can only extend the root Ast, AstToken and AstList and none of
        // these nodes contain additional children.
        //
        // if (! super.equals(o)) return false;
        //
        if (! (o instanceof optFormalArgList)) return false;
        optFormalArgList other = (optFormalArgList) o;
        if (! _formalArgList.equals(other._formalArgList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_formalArgList.hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
            _formalArgList.accept(v);
        v.endVisit(this);
    }
}


