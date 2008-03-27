package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

public interface Visitor extends IAstVisitor
{
    boolean visit(ASTNode n);
    void endVisit(ASTNode n);

    boolean visit(ASTNodeToken n);
    void endVisit(ASTNodeToken n);

    boolean visit(topLevelDeclarationList n);
    void endVisit(topLevelDeclarationList n);

    boolean visit(functionDeclaration n);
    void endVisit(functionDeclaration n);

    boolean visit(functionHeader n);
    void endVisit(functionHeader n);

    boolean visit(declarationList n);
    void endVisit(declarationList n);

    boolean visit(declaration n);
    void endVisit(declaration n);

    boolean visit(algebraicTypeDeclaration n);
    void endVisit(algebraicTypeDeclaration n);

    boolean visit(ctorDeclarationList n);
    void endVisit(ctorDeclarationList n);

    boolean visit(ctorDeclaration n);
    void endVisit(ctorDeclaration n);

    boolean visit(optFormalArgList n);
    void endVisit(optFormalArgList n);

    boolean visit(formalArgList n);
    void endVisit(formalArgList n);

    boolean visit(formalArg n);
    void endVisit(formalArg n);

    boolean visit(typeDef n);
    void endVisit(typeDef n);

    boolean visit(rulesSpecification n);
    void endVisit(rulesSpecification n);

    boolean visit(ruleList n);
    void endVisit(ruleList n);

    boolean visit(rule n);
    void endVisit(rule n);

    boolean visit(analysisSpecification n);
    void endVisit(analysisSpecification n);

    boolean visit(constraintsDef n);
    void endVisit(constraintsDef n);

    boolean visit(estimatesDef n);
    void endVisit(estimatesDef n);

    boolean visit(satisfyDef n);
    void endVisit(satisfyDef n);

    boolean visit(localOrUpdateList n);
    void endVisit(localOrUpdateList n);

    boolean visit(local n);
    void endVisit(local n);

    boolean visit(update n);
    void endVisit(update n);

    boolean visit(variableDeclaration n);
    void endVisit(variableDeclaration n);

    boolean visit(identifier n);
    void endVisit(identifier n);

    boolean visit(tupleLiteral n);
    void endVisit(tupleLiteral n);

    boolean visit(setLiteral n);
    void endVisit(setLiteral n);

    boolean visit(mapLiteral n);
    void endVisit(mapLiteral n);

    boolean visit(mappingList n);
    void endVisit(mappingList n);

    boolean visit(mapping n);
    void endVisit(mapping n);

    boolean visit(relationLiteral n);
    void endVisit(relationLiteral n);

    boolean visit(tupleLiteralList n);
    void endVisit(tupleLiteralList n);

    boolean visit(mapLookup n);
    void endVisit(mapLookup n);

    boolean visit(tupleSlot n);
    void endVisit(tupleSlot n);

    boolean visit(algebraicTypeValue n);
    void endVisit(algebraicTypeValue n);

    boolean visit(optArgList n);
    void endVisit(optArgList n);

    boolean visit(functionCall n);
    void endVisit(functionCall n);

    boolean visit(valueList n);
    void endVisit(valueList n);

    boolean visit(caseExpr n);
    void endVisit(caseExpr n);

    boolean visit(caseSpecList n);
    void endVisit(caseSpecList n);

    boolean visit(unionExpr n);
    void endVisit(unionExpr n);

    boolean visit(intersectExpr n);
    void endVisit(intersectExpr n);

    boolean visit(relationSpecifier n);
    void endVisit(relationSpecifier n);

    boolean visit(tupleSpecifier n);
    void endVisit(tupleSpecifier n);

    boolean visit(setSpecifier n);
    void endVisit(setSpecifier n);

    boolean visit(refSpecifier n);
    void endVisit(refSpecifier n);

    boolean visit(qualifiedIdentifier n);
    void endVisit(qualifiedIdentifier n);

    boolean visit(typeSpecifierList n);
    void endVisit(typeSpecifierList n);

    boolean visit(typeName0 n);
    void endVisit(typeName0 n);

    boolean visit(typeName1 n);
    void endVisit(typeName1 n);

    boolean visit(typeName2 n);
    void endVisit(typeName2 n);

    boolean visit(typeName3 n);
    void endVisit(typeName3 n);

    boolean visit(value0 n);
    void endVisit(value0 n);

    boolean visit(value1 n);
    void endVisit(value1 n);

    boolean visit(value2 n);
    void endVisit(value2 n);

    boolean visit(booleanValue0 n);
    void endVisit(booleanValue0 n);

    boolean visit(booleanValue1 n);
    void endVisit(booleanValue1 n);

    boolean visit(caseSpec0 n);
    void endVisit(caseSpec0 n);

    boolean visit(caseSpec1 n);
    void endVisit(caseSpec1 n);

    boolean visit(relationClosure0 n);
    void endVisit(relationClosure0 n);

    boolean visit(relationClosure1 n);
    void endVisit(relationClosure1 n);

    boolean visit(scalarType0 n);
    void endVisit(scalarType0 n);

    boolean visit(scalarType1 n);
    void endVisit(scalarType1 n);

    boolean visit(scalarType2 n);
    void endVisit(scalarType2 n);

    boolean visit(scalarType3 n);
    void endVisit(scalarType3 n);

}


