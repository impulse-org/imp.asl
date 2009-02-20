
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
        if (! (o instanceof tupleSpecifier)) return false;
        if (! super.equals(o)) return false;
        tupleSpecifier other = (tupleSpecifier) o;
        if (! _typeSpecifierList.equals(other._typeSpecifierList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
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


