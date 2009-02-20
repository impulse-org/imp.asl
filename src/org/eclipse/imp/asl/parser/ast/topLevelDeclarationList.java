
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
 *<li>Rule 2:  compilationUnit ::= $Empty
 *<li>Rule 3:  compilationUnit ::= compilationUnit topLevelDeclaration
 *</b>
 */
public class topLevelDeclarationList extends AbstractASTNodeList implements IcompilationUnit
{
    public ItopLevelDeclaration gettopLevelDeclarationAt(int i) { return (ItopLevelDeclaration) getElementAt(i); }

    public topLevelDeclarationList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public topLevelDeclarationList(ItopLevelDeclaration _topLevelDeclaration, boolean leftRecursive)
    {
        super((ASTNode) _topLevelDeclaration, leftRecursive);
        ((ASTNode) _topLevelDeclaration).setParent(this);
    }

    public void add(ItopLevelDeclaration _topLevelDeclaration)
    {
        super.add((ASTNode) _topLevelDeclaration);
        ((ASTNode) _topLevelDeclaration).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof topLevelDeclarationList)) return false;
        if (! super.equals(o)) return false;
        topLevelDeclarationList other = (topLevelDeclarationList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ItopLevelDeclaration element = gettopLevelDeclarationAt(i);
            if (! element.equals(other.gettopLevelDeclarationAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettopLevelDeclarationAt(i).hashCode());
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
                ItopLevelDeclaration element = gettopLevelDeclarationAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


