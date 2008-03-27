package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 *<b>
 *<li>Rule 91:  caseList ::= caseSpec
 *<li>Rule 92:  caseList ::= caseList caseSpec
 *</b>
 */
public class caseSpecList extends AbstractASTNodeList implements IcaseList
{
    /**
     * The value returned by <b>getcaseSpecAt</b> may be <b>null</b>
     */
    public IcaseSpec getcaseSpecAt(int i) { return (IcaseSpec) getElementAt(i); }

    public caseSpecList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public caseSpecList(IcaseSpec _caseSpec, boolean leftRecursive)
    {
        super((ASTNode) _caseSpec, leftRecursive);
        if (_caseSpec != null) ((ASTNode) _caseSpec).setParent(this);
    }

    public void add(IcaseSpec _caseSpec)
    {
        super.add((ASTNode) _caseSpec);
        if (_caseSpec != null) ((ASTNode) _caseSpec).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof caseSpecList)) return false;
        caseSpecList other = (caseSpecList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IcaseSpec element = getcaseSpecAt(i);
            if (element == null && other.getcaseSpecAt(i) != null) return false;
            else if (! element.equals(other.getcaseSpecAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = 7;
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getcaseSpecAt(i) == null ? 0 : getcaseSpecAt(i).hashCode());
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
                IcaseSpec element = getcaseSpecAt(i);
                if (element != null) element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


