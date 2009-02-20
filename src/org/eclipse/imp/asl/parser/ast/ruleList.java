
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
 *<li>Rule 28:  rulesList ::= rule
 *<li>Rule 29:  rulesList ::= rule rulesList
 *</b>
 */
public class ruleList extends AbstractASTNodeList implements IrulesList
{
    public rule getruleAt(int i) { return (rule) getElementAt(i); }

    public ruleList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ruleList(rule _rule, boolean leftRecursive)
    {
        super((ASTNode) _rule, leftRecursive);
        ((ASTNode) _rule).setParent(this);
    }

    public void add(rule _rule)
    {
        super.add((ASTNode) _rule);
        ((ASTNode) _rule).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ruleList)) return false;
        if (! super.equals(o)) return false;
        ruleList other = (ruleList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            rule element = getruleAt(i);
            if (! element.equals(other.getruleAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getruleAt(i).hashCode());
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
                rule element = getruleAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


