package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 16:  declaration ::= typeSpecifier identifier
 *</b>
 */
public class declaration extends ASTNode implements Ideclaration
{
    private ItypeSpecifier _typeSpecifier;
    private identifier _identifier;

    public ItypeSpecifier gettypeSpecifier() { return _typeSpecifier; }
    public identifier getidentifier() { return _identifier; }

    public declaration(IToken leftIToken, IToken rightIToken,
                       ItypeSpecifier _typeSpecifier,
                       identifier _identifier)
    {
        super(leftIToken, rightIToken);

        this._typeSpecifier = _typeSpecifier;
        ((ASTNode) _typeSpecifier).setParent(this);
        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_typeSpecifier);
        list.add(_identifier);
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
        if (! (o instanceof declaration)) return false;
        declaration other = (declaration) o;
        if (! _typeSpecifier.equals(other._typeSpecifier)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        hash = hash * 31 + (_typeSpecifier.hashCode());
        hash = hash * 31 + (_identifier.hashCode());
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
            _typeSpecifier.accept(v);
            _identifier.accept(v);
        }
        v.endVisit(this);
    }
}


