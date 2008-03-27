package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 65:  tupleLiteral ::= <$ values >$
 *</b>
 */
public class tupleLiteral extends ASTNode implements ItupleLiteral
{
    private valueList _values;

    /**
     * The value returned by <b>getvalues</b> may be <b>null</b>
     */
    public valueList getvalues() { return _values; }

    public tupleLiteral(IToken leftIToken, IToken rightIToken,
                        valueList _values)
    {
        super(leftIToken, rightIToken);

        this._values = _values;
        if (_values != null) ((ASTNode) _values).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_values);
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
        if (! (o instanceof tupleLiteral)) return false;
        tupleLiteral other = (tupleLiteral) o;
        if (_values == null)
            if (other._values != null) return false;
            else; // continue
        else if (! _values.equals(other._values)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_values == null ? 0 : _values.hashCode());
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
            if (_values != null) _values.accept(v);
        v.endVisit(this);
    }
}


