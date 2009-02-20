
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
 *<li>Rule 88:  valueList ::= value
 *<li>Rule 89:  valueList ::= value ,$ valueList
 *</b>
 */
public class valueList extends AbstractASTNodeList implements IvalueList
{
    public Ivalue getvalueAt(int i) { return (Ivalue) getElementAt(i); }

    public valueList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public valueList(Ivalue _value, boolean leftRecursive)
    {
        super((ASTNode) _value, leftRecursive);
        ((ASTNode) _value).setParent(this);
    }

    public void add(Ivalue _value)
    {
        super.add((ASTNode) _value);
        ((ASTNode) _value).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof valueList)) return false;
        if (! super.equals(o)) return false;
        valueList other = (valueList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Ivalue element = getvalueAt(i);
            if (! element.equals(other.getvalueAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getvalueAt(i).hashCode());
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
            for (int i = 0; i < size(); i++)
            {
                Ivalue element = getvalueAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


