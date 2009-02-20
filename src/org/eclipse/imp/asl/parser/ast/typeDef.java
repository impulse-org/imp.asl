
////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2007 IBM Corporation.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//    Philippe Charles (pcharles@us.ibm.com) - initial API and implementation

////////////////////////////////////////////////////////////////////////////////

package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 26:  typeDef ::= type identifier =$ typeSpecifier
 *</b>
 */
public class typeDef extends ASTNode implements ItypeDef
{
    private ASTNodeToken _type;
    private identifier _identifier;
    private ItypeSpecifier _typeSpecifier;

    public ASTNodeToken gettype() { return _type; }
    public identifier getidentifier() { return _identifier; }
    public ItypeSpecifier gettypeSpecifier() { return _typeSpecifier; }

    public typeDef(IToken leftIToken, IToken rightIToken,
                   ASTNodeToken _type,
                   identifier _identifier,
                   ItypeSpecifier _typeSpecifier)
    {
        super(leftIToken, rightIToken);

        this._type = _type;
        ((ASTNode) _type).setParent(this);
        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
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
        list.add(_type);
        list.add(_identifier);
        list.add(_typeSpecifier);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof typeDef)) return false;
        if (! super.equals(o)) return false;
        typeDef other = (typeDef) o;
        if (! _type.equals(other._type)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _typeSpecifier.equals(other._typeSpecifier)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_type.hashCode());
        hash = hash * 31 + (_identifier.hashCode());
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
            _type.accept(v);
            _identifier.accept(v);
            _typeSpecifier.accept(v);
        }
        v.endVisit(this);
    }
}


