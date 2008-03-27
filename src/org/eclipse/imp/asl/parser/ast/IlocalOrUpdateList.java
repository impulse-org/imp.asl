package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>localOrUpdateList
 *<li>local
 *<li>update
 *</ul>
 *</b>
 */
public interface IlocalOrUpdateList
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


