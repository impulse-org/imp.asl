package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>functionDeclaration
 *<li>algebraicTypeDeclaration
 *<li>typeDef
 *<li>rulesSpecification
 *<li>analysisSpecification
 *<li>variableDeclaration
 *</ul>
 *</b>
 */
public interface ItopLevelDeclaration
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


