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
 *<li>Rule 116:  typeSpecifierList ::= typeSpecifier
 *<li>Rule 117:  typeSpecifierList ::= typeSpecifierList ,$ typeSpecifier
 *</b>
 */
public class typeSpecifierList extends AbstractASTNodeList implements ItypeSpecifierList
{
    public ItypeSpecifier gettypeSpecifierAt(int i) { return (ItypeSpecifier) getElementAt(i); }

    public typeSpecifierList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public typeSpecifierList(ItypeSpecifier _typeSpecifier, boolean leftRecursive)
    {
        super((ASTNode) _typeSpecifier, leftRecursive);
        ((ASTNode) _typeSpecifier).setParent(this);
    }

    public void add(ItypeSpecifier _typeSpecifier)
    {
        super.add((ASTNode) _typeSpecifier);
        ((ASTNode) _typeSpecifier).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof typeSpecifierList)) return false;
        typeSpecifierList other = (typeSpecifierList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ItypeSpecifier element = gettypeSpecifierAt(i);
            if (! element.equals(other.gettypeSpecifierAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (gettypeSpecifierAt(i).hashCode());
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
                ItypeSpecifier element = gettypeSpecifierAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


