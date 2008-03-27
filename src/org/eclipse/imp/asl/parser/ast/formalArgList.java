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
 *<li>Rule 23:  formalArgList ::= formalArg
 *<li>Rule 24:  formalArgList ::= formalArgList ,$ formalArg
 *</b>
 */
public class formalArgList extends AbstractASTNodeList implements IformalArgList
{
    public formalArg getformalArgAt(int i) { return (formalArg) getElementAt(i); }

    public formalArgList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public formalArgList(formalArg _formalArg, boolean leftRecursive)
    {
        super((ASTNode) _formalArg, leftRecursive);
        ((ASTNode) _formalArg).setParent(this);
    }

    public void add(formalArg _formalArg)
    {
        super.add((ASTNode) _formalArg);
        ((ASTNode) _formalArg).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof formalArgList)) return false;
        formalArgList other = (formalArgList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            formalArg element = getformalArgAt(i);
            if (! element.equals(other.getformalArgAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getformalArgAt(i).hashCode());
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
                formalArg element = getformalArgAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


