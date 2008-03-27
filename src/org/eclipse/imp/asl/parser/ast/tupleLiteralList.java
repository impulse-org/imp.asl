/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 76:  tupleList ::= tupleLiteral
 *<li>Rule 77:  tupleList ::= tupleLiteral ,$ tupleList
 *</b>
 */
public class tupleLiteralList extends AbstractASTNodeList implements ItupleList
{
    public tupleLiteral gettupleLiteralAt(int i) { return (tupleLiteral) getElementAt(i); }

    public tupleLiteralList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public tupleLiteralList(tupleLiteral _tupleLiteral, boolean leftRecursive)
    {
        super((ASTNode) _tupleLiteral, leftRecursive);
        ((ASTNode) _tupleLiteral).setParent(this);
    }

    public void add(tupleLiteral _tupleLiteral)
    {
        super.add((ASTNode) _tupleLiteral);
        ((ASTNode) _tupleLiteral).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof tupleLiteralList)) return false;
        tupleLiteralList other = (tupleLiteralList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            tupleLiteral element = gettupleLiteralAt(i);
            if (! element.equals(other.gettupleLiteralAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettupleLiteralAt(i).hashCode());
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
                tupleLiteral element = gettupleLiteralAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


