package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 108:  setSpecifier ::= set [$ typeSpecifier ]$
 *</b>
 */
public class setSpecifier extends ASTNode implements IsetSpecifier
{
    private ASTNodeToken _set;
    private ItypeSpecifier _typeSpecifier;

    public ASTNodeToken getset() { return _set; }
    public ItypeSpecifier gettypeSpecifier() { return _typeSpecifier; }

    public setSpecifier(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _set,
                        ItypeSpecifier _typeSpecifier)
    {
        super(leftIToken, rightIToken);

        this._set = _set;
        ((ASTNode) _set).setParent(this);
        this._typeSpecifier = _typeSpecifier;
        ((ASTNode) _typeSpecifier).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_set);
        list.add(_typeSpecifier);
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
        if (! (o instanceof setSpecifier)) return false;
        setSpecifier other = (setSpecifier) o;
        if (! _set.equals(other._set)) return false;
        if (! _typeSpecifier.equals(other._typeSpecifier)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_set.hashCode());
        hash = hash * 31 + (_typeSpecifier.hashCode());
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
        {
            _set.accept(v);
            _typeSpecifier.accept(v);
        }
        v.endVisit(this);
    }
}


