
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
 *<em>
 *<li>Rule 35:  localOrUpdateList ::= localOrUpdate
 *</em>
 *<p>
 *<b>
 *<li>Rule 36:  localOrUpdateList ::= localOrUpdateList localOrUpdate
 *</b>
 */
public class localOrUpdateList extends ASTNode implements IlocalOrUpdateList
{
    private IlocalOrUpdateList _localOrUpdateList;
    private IlocalOrUpdate _localOrUpdate;

    public IlocalOrUpdateList getlocalOrUpdateList() { return _localOrUpdateList; }
    public IlocalOrUpdate getlocalOrUpdate() { return _localOrUpdate; }

    public localOrUpdateList(IToken leftIToken, IToken rightIToken,
                             IlocalOrUpdateList _localOrUpdateList,
                             IlocalOrUpdate _localOrUpdate)
    {
        super(leftIToken, rightIToken);

        this._localOrUpdateList = _localOrUpdateList;
        ((ASTNode) _localOrUpdateList).setParent(this);
        this._localOrUpdate = _localOrUpdate;
        ((ASTNode) _localOrUpdate).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_localOrUpdateList);
        list.add(_localOrUpdate);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof localOrUpdateList)) return false;
        if (! super.equals(o)) return false;
        localOrUpdateList other = (localOrUpdateList) o;
        if (! _localOrUpdateList.equals(other._localOrUpdateList)) return false;
        if (! _localOrUpdate.equals(other._localOrUpdate)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_localOrUpdateList.hashCode());
        hash = hash * 31 + (_localOrUpdate.hashCode());
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
            _localOrUpdateList.accept(v);
            _localOrUpdate.accept(v);
        }
        v.endVisit(this);
    }
}


