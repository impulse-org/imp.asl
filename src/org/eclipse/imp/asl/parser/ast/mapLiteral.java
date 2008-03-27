package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 67:  mapLiteral ::= {$ mappings }$
 *</b>
 */
public class mapLiteral extends ASTNode implements ImapLiteral
{
    private mappingList _mappings;

    /**
     * The value returned by <b>getmappings</b> may be <b>null</b>
     */
    public mappingList getmappings() { return _mappings; }

    public mapLiteral(IToken leftIToken, IToken rightIToken,
                      mappingList _mappings)
    {
        super(leftIToken, rightIToken);

        this._mappings = _mappings;
        if (_mappings != null) ((ASTNode) _mappings).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_mappings);
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
        if (! (o instanceof mapLiteral)) return false;
        mapLiteral other = (mapLiteral) o;
        if (_mappings == null)
            if (other._mappings != null) return false;
            else; // continue
        else if (! _mappings.equals(other._mappings)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_mappings == null ? 0 : _mappings.hashCode());
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
            if (_mappings != null) _mappings.accept(v);
        v.endVisit(this);
    }
}


