
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
        if (! (o instanceof setSpecifier)) return false;
        if (! super.equals(o)) return false;
        setSpecifier other = (setSpecifier) o;
        if (! _set.equals(other._set)) return false;
        if (! _typeSpecifier.equals(other._typeSpecifier)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
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


