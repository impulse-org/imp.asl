package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

/**
 * is implemented by:
 *<b>
 *<ul>
 *<li>identifier
 *<li>relationSpecifier
 *<li>tupleSpecifier
 *<li>setSpecifier
 *<li>refSpecifier
 *<li>qualifiedIdentifier
 *<li>scalarType0
 *<li>scalarType1
 *<li>scalarType2
 *<li>scalarType3
 *</ul>
 *</b>
 */
public interface ItypeSpecifier
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


