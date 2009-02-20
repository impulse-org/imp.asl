
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
 *<li>Rule 11:  functionHeader ::= typeSpecifier identifier ($ parameters )$
 *</b>
 */
public class functionHeader extends ASTNode implements IfunctionHeader
{
    private ItypeSpecifier _typeSpecifier;
    private identifier _identifier;
    private declarationList _parameters;

    public ItypeSpecifier gettypeSpecifier() { return _typeSpecifier; }
    public identifier getidentifier() { return _identifier; }
    public declarationList getparameters() { return _parameters; }

    public functionHeader(IToken leftIToken, IToken rightIToken,
                          ItypeSpecifier _typeSpecifier,
                          identifier _identifier,
                          declarationList _parameters)
    {
        super(leftIToken, rightIToken);

        this._typeSpecifier = _typeSpecifier;
        ((ASTNode) _typeSpecifier).setParent(this);
        this._identifier = _identifier;
        ((ASTNode) _identifier).setParent(this);
        this._parameters = _parameters;
        ((ASTNode) _parameters).setParent(this);
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
        list.add(_parameters);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof functionHeader)) return false;
        if (! super.equals(o)) return false;
        functionHeader other = (functionHeader) o;
        if (! _typeSpecifier.equals(other._typeSpecifier)) return false;
        if (! _identifier.equals(other._identifier)) return false;
        if (! _parameters.equals(other._parameters)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_typeSpecifier.hashCode());
        hash = hash * 31 + (_identifier.hashCode());
        hash = hash * 31 + (_parameters.hashCode());
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
            _parameters.accept(v);
        }
        v.endVisit(this);
    }
}


