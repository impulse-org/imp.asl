
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
 *<li>Rule 70:  mappingList ::= mapping
 *<li>Rule 71:  mappingList ::= mappingList mapping
 *</b>
 */
public class mappingList extends AbstractASTNodeList implements ImappingList
{
    public mapping getmappingAt(int i) { return (mapping) getElementAt(i); }

    public mappingList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public mappingList(mapping _mapping, boolean leftRecursive)
    {
        super((ASTNode) _mapping, leftRecursive);
        ((ASTNode) _mapping).setParent(this);
    }

    public void add(mapping _mapping)
    {
        super.add((ASTNode) _mapping);
        ((ASTNode) _mapping).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof mappingList)) return false;
        if (! super.equals(o)) return false;
        mappingList other = (mappingList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            mapping element = getmappingAt(i);
            if (! element.equals(other.getmappingAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getmappingAt(i).hashCode());
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
                mapping element = getmappingAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


