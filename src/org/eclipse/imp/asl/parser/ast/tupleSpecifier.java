package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 107:  tupleSpecifier ::= <$ typeSpecifierList >$
 *</b>
 */
public class tupleSpecifier extends ASTNode implements ItupleSpecifier
{
    private typeSpecifierList _typeSpecifierList;

    public typeSpecifierList gettypeSpecifierList() { return _typeSpecifierList; }

    public tupleSpecifier(IToken leftIToken, IToken rightIToken,
                          typeSpecifierList _typeSpecifierList)
    {
        super(leftIToken, rightIToken);

        this._typeSpecifierList = _typeSpecifierList;
        ((ASTNode) _typeSpecifierList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_typeSpecifierList);
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
        if (! (o instanceof tupleSpecifier)) return false;
        tupleSpecifier other = (tupleSpecifier) o;
        if (! _typeSpecifierList.equals(other._typeSpecifierList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_typeSpecifierList.hashCode());
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
            _typeSpecifierList.accept(v);
        v.endVisit(this);
    }
}


