package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>caseSpec0
 *<li>caseSpec1
 *</ul>
 *</b>
 */
public interface IcaseSpec
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


