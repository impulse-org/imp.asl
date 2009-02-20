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

package org.eclipse.imp.asl.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.eclipse.imp.asl.parser.ast.ASTNode;
import org.eclipse.imp.asl.parser.ast.AbstractVisitor;
import org.eclipse.imp.asl.parser.ast.qualifiedIdentifier;
import org.eclipse.imp.asl.parser.ast.relationSpecifier;
import org.eclipse.imp.asl.parser.ast.scalarType0;
import org.eclipse.imp.asl.parser.ast.scalarType1;
import org.eclipse.imp.asl.parser.ast.scalarType2;
import org.eclipse.imp.asl.parser.ast.scalarType3;
import org.eclipse.imp.asl.parser.ast.setSpecifier;
import org.eclipse.imp.asl.parser.ast.tupleSpecifier;
import org.eclipse.imp.pdb.facts.type.ITypeVisitor;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;

public class TypeSpecifierParser {
    private ASLLexer fTypeSpecLexer;
    private ASLParser fTypeSpecParser;
    protected TypeFactory tf = TypeFactory.getInstance();
    
    public TypeSpecifierParser() {
        fTypeSpecLexer = new ASLLexer();
        fTypeSpecParser = new ASLParser();
    }
    
    private final class MarkerType extends Type {
        @Override
        public boolean isSubtypeOf(Type other) {
            throw new UnsupportedOperationException();
        }
        @Override
        public Type lub(Type other) {
            throw new UnsupportedOperationException();
        }
        @Override
        public <T> T accept(ITypeVisitor<T> visitor) {
            throw new UnsupportedOperationException();
        }
    }

    private Type parseTypeSpecifier(String typeSpec) {
        char[] contentsArray= typeSpec.toCharArray();
        fTypeSpecLexer.reset(contentsArray, null /*fullFilePath.toOSString()*/);
        fTypeSpecParser.reset(fTypeSpecLexer.getILexStream());
        fTypeSpecParser.getIPrsStream().setMessageHandler(new NullMessageHandler());
        fTypeSpecLexer.lexer(null, fTypeSpecParser.getIPrsStream());

        ASTNode root= (ASTNode) fTypeSpecParser.parsetypeSpecifier(null, 0);

        if (root == null) {
            return null;
        }

        final Stack<Type> fStack= new Stack<Type>();

        root.accept(new AbstractVisitor() {
            private void pushMark() {
                fStack.push(new MarkerType());
            }
            private List<Type> popToMark() {
                List<Type> result= new ArrayList<Type>();
                Type item;
                do {
                    item= fStack.pop();
                    if (item instanceof MarkerType) {
                        break;
                    }
                    result.add(item);
                } while(true);
                Collections.reverse(result);
                return result;
            }
            @Override
            public void unimplementedVisitor(String s) { }
            @Override
            public boolean visit(relationSpecifier n) {
                pushMark();
                return super.visit(n);
            }
            @Override
            public void endVisit(relationSpecifier n) {
                List<Type> fieldTypes= popToMark();
                fStack.push(tf.relType(tf.tupleType(fieldTypes.toArray())));
            }
            @Override
            public boolean visit(tupleSpecifier n) {
                pushMark();
                return super.visit(n);
            }
            @Override
            public void endVisit(tupleSpecifier n) {
                List<Type> fieldTypes= popToMark();
                fStack.push(tf.tupleType(fieldTypes.toArray()));
            }
            @Override
            public void endVisit(setSpecifier n) {
                Type elemType= fStack.pop();
                fStack.push(tf.setType(elemType));
            }
            @Override
            public void endVisit(scalarType0 n) {
                fStack.push(tf.integerType());
            }
            @Override
            public void endVisit(scalarType1 n) {
                fStack.push(tf.doubleType());
            }
            @Override
            public void endVisit(scalarType2 n) {
                fStack.push(tf.stringType());
            }
            @Override
            public void endVisit(qualifiedIdentifier n) {
                Type nType= tf.lookupNamedType(n.toString());
                if (nType != null) {
                    fStack.push(nType);
                }
            }
            @Override
            public void endVisit(scalarType3 n) {
//              fStack.push(booleanType());
                throw new UnsupportedOperationException("No support for boolean types yet");
            }
        });
        return fStack.pop();
    }

}
