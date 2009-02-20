
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
 *<li>Rule 18:  ctorList ::= ctorDeclaration
 *<li>Rule 19:  ctorList ::= ctorList |$ ctorDeclaration
 *</b>
 */
public class ctorDeclarationList extends AbstractASTNodeList implements IctorList
{
    public ctorDeclaration getctorDeclarationAt(int i) { return (ctorDeclaration) getElementAt(i); }

    public ctorDeclarationList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ctorDeclarationList(ctorDeclaration _ctorDeclaration, boolean leftRecursive)
    {
        super((ASTNode) _ctorDeclaration, leftRecursive);
        ((ASTNode) _ctorDeclaration).setParent(this);
    }

    public void add(ctorDeclaration _ctorDeclaration)
    {
        super.add((ASTNode) _ctorDeclaration);
        ((ASTNode) _ctorDeclaration).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ctorDeclarationList)) return false;
        if (! super.equals(o)) return false;
        ctorDeclarationList other = (ctorDeclarationList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ctorDeclaration element = getctorDeclarationAt(i);
            if (! element.equals(other.getctorDeclarationAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getctorDeclarationAt(i).hashCode());
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
                ctorDeclaration element = getctorDeclarationAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


