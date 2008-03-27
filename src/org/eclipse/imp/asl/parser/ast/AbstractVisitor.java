package org.eclipse.imp.asl.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;

public abstract class AbstractVisitor implements Visitor
{
    public abstract void unimplementedVisitor(String s);

    public boolean preVisit(IAst element) { return true; }

    public void postVisit(IAst element) {}

    public boolean visit(ASTNodeToken n) { unimplementedVisitor("visit(ASTNodeToken)"); return true; }
    public void endVisit(ASTNodeToken n) { unimplementedVisitor("endVisit(ASTNodeToken)"); }

    public boolean visit(topLevelDeclarationList n) { unimplementedVisitor("visit(topLevelDeclarationList)"); return true; }
    public void endVisit(topLevelDeclarationList n) { unimplementedVisitor("endVisit(topLevelDeclarationList)"); }

    public boolean visit(functionDeclaration n) { unimplementedVisitor("visit(functionDeclaration)"); return true; }
    public void endVisit(functionDeclaration n) { unimplementedVisitor("endVisit(functionDeclaration)"); }

    public boolean visit(functionHeader n) { unimplementedVisitor("visit(functionHeader)"); return true; }
    public void endVisit(functionHeader n) { unimplementedVisitor("endVisit(functionHeader)"); }

    public boolean visit(declarationList n) { unimplementedVisitor("visit(declarationList)"); return true; }
    public void endVisit(declarationList n) { unimplementedVisitor("endVisit(declarationList)"); }

    public boolean visit(declaration n) { unimplementedVisitor("visit(declaration)"); return true; }
    public void endVisit(declaration n) { unimplementedVisitor("endVisit(declaration)"); }

    public boolean visit(algebraicTypeDeclaration n) { unimplementedVisitor("visit(algebraicTypeDeclaration)"); return true; }
    public void endVisit(algebraicTypeDeclaration n) { unimplementedVisitor("endVisit(algebraicTypeDeclaration)"); }

    public boolean visit(ctorDeclarationList n) { unimplementedVisitor("visit(ctorDeclarationList)"); return true; }
    public void endVisit(ctorDeclarationList n) { unimplementedVisitor("endVisit(ctorDeclarationList)"); }

    public boolean visit(ctorDeclaration n) { unimplementedVisitor("visit(ctorDeclaration)"); return true; }
    public void endVisit(ctorDeclaration n) { unimplementedVisitor("endVisit(ctorDeclaration)"); }

    public boolean visit(optFormalArgList n) { unimplementedVisitor("visit(optFormalArgList)"); return true; }
    public void endVisit(optFormalArgList n) { unimplementedVisitor("endVisit(optFormalArgList)"); }

    public boolean visit(formalArgList n) { unimplementedVisitor("visit(formalArgList)"); return true; }
    public void endVisit(formalArgList n) { unimplementedVisitor("endVisit(formalArgList)"); }

    public boolean visit(formalArg n) { unimplementedVisitor("visit(formalArg)"); return true; }
    public void endVisit(formalArg n) { unimplementedVisitor("endVisit(formalArg)"); }

    public boolean visit(typeDef n) { unimplementedVisitor("visit(typeDef)"); return true; }
    public void endVisit(typeDef n) { unimplementedVisitor("endVisit(typeDef)"); }

    public boolean visit(rulesSpecification n) { unimplementedVisitor("visit(rulesSpecification)"); return true; }
    public void endVisit(rulesSpecification n) { unimplementedVisitor("endVisit(rulesSpecification)"); }

    public boolean visit(ruleList n) { unimplementedVisitor("visit(ruleList)"); return true; }
    public void endVisit(ruleList n) { unimplementedVisitor("endVisit(ruleList)"); }

    public boolean visit(rule n) { unimplementedVisitor("visit(rule)"); return true; }
    public void endVisit(rule n) { unimplementedVisitor("endVisit(rule)"); }

    public boolean visit(analysisSpecification n) { unimplementedVisitor("visit(analysisSpecification)"); return true; }
    public void endVisit(analysisSpecification n) { unimplementedVisitor("endVisit(analysisSpecification)"); }

    public boolean visit(constraintsDef n) { unimplementedVisitor("visit(constraintsDef)"); return true; }
    public void endVisit(constraintsDef n) { unimplementedVisitor("endVisit(constraintsDef)"); }

    public boolean visit(estimatesDef n) { unimplementedVisitor("visit(estimatesDef)"); return true; }
    public void endVisit(estimatesDef n) { unimplementedVisitor("endVisit(estimatesDef)"); }

    public boolean visit(satisfyDef n) { unimplementedVisitor("visit(satisfyDef)"); return true; }
    public void endVisit(satisfyDef n) { unimplementedVisitor("endVisit(satisfyDef)"); }

    public boolean visit(localOrUpdateList n) { unimplementedVisitor("visit(localOrUpdateList)"); return true; }
    public void endVisit(localOrUpdateList n) { unimplementedVisitor("endVisit(localOrUpdateList)"); }

    public boolean visit(local n) { unimplementedVisitor("visit(local)"); return true; }
    public void endVisit(local n) { unimplementedVisitor("endVisit(local)"); }

    public boolean visit(update n) { unimplementedVisitor("visit(update)"); return true; }
    public void endVisit(update n) { unimplementedVisitor("endVisit(update)"); }

    public boolean visit(variableDeclaration n) { unimplementedVisitor("visit(variableDeclaration)"); return true; }
    public void endVisit(variableDeclaration n) { unimplementedVisitor("endVisit(variableDeclaration)"); }

    public boolean visit(identifier n) { unimplementedVisitor("visit(identifier)"); return true; }
    public void endVisit(identifier n) { unimplementedVisitor("endVisit(identifier)"); }

    public boolean visit(tupleLiteral n) { unimplementedVisitor("visit(tupleLiteral)"); return true; }
    public void endVisit(tupleLiteral n) { unimplementedVisitor("endVisit(tupleLiteral)"); }

    public boolean visit(setLiteral n) { unimplementedVisitor("visit(setLiteral)"); return true; }
    public void endVisit(setLiteral n) { unimplementedVisitor("endVisit(setLiteral)"); }

    public boolean visit(mapLiteral n) { unimplementedVisitor("visit(mapLiteral)"); return true; }
    public void endVisit(mapLiteral n) { unimplementedVisitor("endVisit(mapLiteral)"); }

    public boolean visit(mappingList n) { unimplementedVisitor("visit(mappingList)"); return true; }
    public void endVisit(mappingList n) { unimplementedVisitor("endVisit(mappingList)"); }

    public boolean visit(mapping n) { unimplementedVisitor("visit(mapping)"); return true; }
    public void endVisit(mapping n) { unimplementedVisitor("endVisit(mapping)"); }

    public boolean visit(relationLiteral n) { unimplementedVisitor("visit(relationLiteral)"); return true; }
    public void endVisit(relationLiteral n) { unimplementedVisitor("endVisit(relationLiteral)"); }

    public boolean visit(tupleLiteralList n) { unimplementedVisitor("visit(tupleLiteralList)"); return true; }
    public void endVisit(tupleLiteralList n) { unimplementedVisitor("endVisit(tupleLiteralList)"); }

    public boolean visit(mapLookup n) { unimplementedVisitor("visit(mapLookup)"); return true; }
    public void endVisit(mapLookup n) { unimplementedVisitor("endVisit(mapLookup)"); }

    public boolean visit(tupleSlot n) { unimplementedVisitor("visit(tupleSlot)"); return true; }
    public void endVisit(tupleSlot n) { unimplementedVisitor("endVisit(tupleSlot)"); }

    public boolean visit(algebraicTypeValue n) { unimplementedVisitor("visit(algebraicTypeValue)"); return true; }
    public void endVisit(algebraicTypeValue n) { unimplementedVisitor("endVisit(algebraicTypeValue)"); }

    public boolean visit(optArgList n) { unimplementedVisitor("visit(optArgList)"); return true; }
    public void endVisit(optArgList n) { unimplementedVisitor("endVisit(optArgList)"); }

    public boolean visit(functionCall n) { unimplementedVisitor("visit(functionCall)"); return true; }
    public void endVisit(functionCall n) { unimplementedVisitor("endVisit(functionCall)"); }

    public boolean visit(valueList n) { unimplementedVisitor("visit(valueList)"); return true; }
    public void endVisit(valueList n) { unimplementedVisitor("endVisit(valueList)"); }

    public boolean visit(caseExpr n) { unimplementedVisitor("visit(caseExpr)"); return true; }
    public void endVisit(caseExpr n) { unimplementedVisitor("endVisit(caseExpr)"); }

    public boolean visit(caseSpecList n) { unimplementedVisitor("visit(caseSpecList)"); return true; }
    public void endVisit(caseSpecList n) { unimplementedVisitor("endVisit(caseSpecList)"); }

    public boolean visit(unionExpr n) { unimplementedVisitor("visit(unionExpr)"); return true; }
    public void endVisit(unionExpr n) { unimplementedVisitor("endVisit(unionExpr)"); }

    public boolean visit(intersectExpr n) { unimplementedVisitor("visit(intersectExpr)"); return true; }
    public void endVisit(intersectExpr n) { unimplementedVisitor("endVisit(intersectExpr)"); }

    public boolean visit(relationSpecifier n) { unimplementedVisitor("visit(relationSpecifier)"); return true; }
    public void endVisit(relationSpecifier n) { unimplementedVisitor("endVisit(relationSpecifier)"); }

    public boolean visit(tupleSpecifier n) { unimplementedVisitor("visit(tupleSpecifier)"); return true; }
    public void endVisit(tupleSpecifier n) { unimplementedVisitor("endVisit(tupleSpecifier)"); }

    public boolean visit(setSpecifier n) { unimplementedVisitor("visit(setSpecifier)"); return true; }
    public void endVisit(setSpecifier n) { unimplementedVisitor("endVisit(setSpecifier)"); }

    public boolean visit(refSpecifier n) { unimplementedVisitor("visit(refSpecifier)"); return true; }
    public void endVisit(refSpecifier n) { unimplementedVisitor("endVisit(refSpecifier)"); }

    public boolean visit(qualifiedIdentifier n) { unimplementedVisitor("visit(qualifiedIdentifier)"); return true; }
    public void endVisit(qualifiedIdentifier n) { unimplementedVisitor("endVisit(qualifiedIdentifier)"); }

    public boolean visit(typeSpecifierList n) { unimplementedVisitor("visit(typeSpecifierList)"); return true; }
    public void endVisit(typeSpecifierList n) { unimplementedVisitor("endVisit(typeSpecifierList)"); }

    public boolean visit(typeName0 n) { unimplementedVisitor("visit(typeName0)"); return true; }
    public void endVisit(typeName0 n) { unimplementedVisitor("endVisit(typeName0)"); }

    public boolean visit(typeName1 n) { unimplementedVisitor("visit(typeName1)"); return true; }
    public void endVisit(typeName1 n) { unimplementedVisitor("endVisit(typeName1)"); }

    public boolean visit(typeName2 n) { unimplementedVisitor("visit(typeName2)"); return true; }
    public void endVisit(typeName2 n) { unimplementedVisitor("endVisit(typeName2)"); }

    public boolean visit(typeName3 n) { unimplementedVisitor("visit(typeName3)"); return true; }
    public void endVisit(typeName3 n) { unimplementedVisitor("endVisit(typeName3)"); }

    public boolean visit(value0 n) { unimplementedVisitor("visit(value0)"); return true; }
    public void endVisit(value0 n) { unimplementedVisitor("endVisit(value0)"); }

    public boolean visit(value1 n) { unimplementedVisitor("visit(value1)"); return true; }
    public void endVisit(value1 n) { unimplementedVisitor("endVisit(value1)"); }

    public boolean visit(value2 n) { unimplementedVisitor("visit(value2)"); return true; }
    public void endVisit(value2 n) { unimplementedVisitor("endVisit(value2)"); }

    public boolean visit(booleanValue0 n) { unimplementedVisitor("visit(booleanValue0)"); return true; }
    public void endVisit(booleanValue0 n) { unimplementedVisitor("endVisit(booleanValue0)"); }

    public boolean visit(booleanValue1 n) { unimplementedVisitor("visit(booleanValue1)"); return true; }
    public void endVisit(booleanValue1 n) { unimplementedVisitor("endVisit(booleanValue1)"); }

    public boolean visit(caseSpec0 n) { unimplementedVisitor("visit(caseSpec0)"); return true; }
    public void endVisit(caseSpec0 n) { unimplementedVisitor("endVisit(caseSpec0)"); }

    public boolean visit(caseSpec1 n) { unimplementedVisitor("visit(caseSpec1)"); return true; }
    public void endVisit(caseSpec1 n) { unimplementedVisitor("endVisit(caseSpec1)"); }

    public boolean visit(relationClosure0 n) { unimplementedVisitor("visit(relationClosure0)"); return true; }
    public void endVisit(relationClosure0 n) { unimplementedVisitor("endVisit(relationClosure0)"); }

    public boolean visit(relationClosure1 n) { unimplementedVisitor("visit(relationClosure1)"); return true; }
    public void endVisit(relationClosure1 n) { unimplementedVisitor("endVisit(relationClosure1)"); }

    public boolean visit(scalarType0 n) { unimplementedVisitor("visit(scalarType0)"); return true; }
    public void endVisit(scalarType0 n) { unimplementedVisitor("endVisit(scalarType0)"); }

    public boolean visit(scalarType1 n) { unimplementedVisitor("visit(scalarType1)"); return true; }
    public void endVisit(scalarType1 n) { unimplementedVisitor("endVisit(scalarType1)"); }

    public boolean visit(scalarType2 n) { unimplementedVisitor("visit(scalarType2)"); return true; }
    public void endVisit(scalarType2 n) { unimplementedVisitor("endVisit(scalarType2)"); }

    public boolean visit(scalarType3 n) { unimplementedVisitor("visit(scalarType3)"); return true; }
    public void endVisit(scalarType3 n) { unimplementedVisitor("endVisit(scalarType3)"); }


    public boolean visit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) return visit((ASTNodeToken) n);
        else if (n instanceof topLevelDeclarationList) return visit((topLevelDeclarationList) n);
        else if (n instanceof functionDeclaration) return visit((functionDeclaration) n);
        else if (n instanceof functionHeader) return visit((functionHeader) n);
        else if (n instanceof declarationList) return visit((declarationList) n);
        else if (n instanceof declaration) return visit((declaration) n);
        else if (n instanceof algebraicTypeDeclaration) return visit((algebraicTypeDeclaration) n);
        else if (n instanceof ctorDeclarationList) return visit((ctorDeclarationList) n);
        else if (n instanceof ctorDeclaration) return visit((ctorDeclaration) n);
        else if (n instanceof optFormalArgList) return visit((optFormalArgList) n);
        else if (n instanceof formalArgList) return visit((formalArgList) n);
        else if (n instanceof formalArg) return visit((formalArg) n);
        else if (n instanceof typeDef) return visit((typeDef) n);
        else if (n instanceof rulesSpecification) return visit((rulesSpecification) n);
        else if (n instanceof ruleList) return visit((ruleList) n);
        else if (n instanceof rule) return visit((rule) n);
        else if (n instanceof analysisSpecification) return visit((analysisSpecification) n);
        else if (n instanceof constraintsDef) return visit((constraintsDef) n);
        else if (n instanceof estimatesDef) return visit((estimatesDef) n);
        else if (n instanceof satisfyDef) return visit((satisfyDef) n);
        else if (n instanceof localOrUpdateList) return visit((localOrUpdateList) n);
        else if (n instanceof local) return visit((local) n);
        else if (n instanceof update) return visit((update) n);
        else if (n instanceof variableDeclaration) return visit((variableDeclaration) n);
        else if (n instanceof identifier) return visit((identifier) n);
        else if (n instanceof tupleLiteral) return visit((tupleLiteral) n);
        else if (n instanceof setLiteral) return visit((setLiteral) n);
        else if (n instanceof mapLiteral) return visit((mapLiteral) n);
        else if (n instanceof mappingList) return visit((mappingList) n);
        else if (n instanceof mapping) return visit((mapping) n);
        else if (n instanceof relationLiteral) return visit((relationLiteral) n);
        else if (n instanceof tupleLiteralList) return visit((tupleLiteralList) n);
        else if (n instanceof mapLookup) return visit((mapLookup) n);
        else if (n instanceof tupleSlot) return visit((tupleSlot) n);
        else if (n instanceof algebraicTypeValue) return visit((algebraicTypeValue) n);
        else if (n instanceof optArgList) return visit((optArgList) n);
        else if (n instanceof functionCall) return visit((functionCall) n);
        else if (n instanceof valueList) return visit((valueList) n);
        else if (n instanceof caseExpr) return visit((caseExpr) n);
        else if (n instanceof caseSpecList) return visit((caseSpecList) n);
        else if (n instanceof unionExpr) return visit((unionExpr) n);
        else if (n instanceof intersectExpr) return visit((intersectExpr) n);
        else if (n instanceof relationSpecifier) return visit((relationSpecifier) n);
        else if (n instanceof tupleSpecifier) return visit((tupleSpecifier) n);
        else if (n instanceof setSpecifier) return visit((setSpecifier) n);
        else if (n instanceof refSpecifier) return visit((refSpecifier) n);
        else if (n instanceof qualifiedIdentifier) return visit((qualifiedIdentifier) n);
        else if (n instanceof typeSpecifierList) return visit((typeSpecifierList) n);
        else if (n instanceof typeName0) return visit((typeName0) n);
        else if (n instanceof typeName1) return visit((typeName1) n);
        else if (n instanceof typeName2) return visit((typeName2) n);
        else if (n instanceof typeName3) return visit((typeName3) n);
        else if (n instanceof value0) return visit((value0) n);
        else if (n instanceof value1) return visit((value1) n);
        else if (n instanceof value2) return visit((value2) n);
        else if (n instanceof booleanValue0) return visit((booleanValue0) n);
        else if (n instanceof booleanValue1) return visit((booleanValue1) n);
        else if (n instanceof caseSpec0) return visit((caseSpec0) n);
        else if (n instanceof caseSpec1) return visit((caseSpec1) n);
        else if (n instanceof relationClosure0) return visit((relationClosure0) n);
        else if (n instanceof relationClosure1) return visit((relationClosure1) n);
        else if (n instanceof scalarType0) return visit((scalarType0) n);
        else if (n instanceof scalarType1) return visit((scalarType1) n);
        else if (n instanceof scalarType2) return visit((scalarType2) n);
        else if (n instanceof scalarType3) return visit((scalarType3) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
    public void endVisit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) endVisit((ASTNodeToken) n);
        else if (n instanceof topLevelDeclarationList) endVisit((topLevelDeclarationList) n);
        else if (n instanceof functionDeclaration) endVisit((functionDeclaration) n);
        else if (n instanceof functionHeader) endVisit((functionHeader) n);
        else if (n instanceof declarationList) endVisit((declarationList) n);
        else if (n instanceof declaration) endVisit((declaration) n);
        else if (n instanceof algebraicTypeDeclaration) endVisit((algebraicTypeDeclaration) n);
        else if (n instanceof ctorDeclarationList) endVisit((ctorDeclarationList) n);
        else if (n instanceof ctorDeclaration) endVisit((ctorDeclaration) n);
        else if (n instanceof optFormalArgList) endVisit((optFormalArgList) n);
        else if (n instanceof formalArgList) endVisit((formalArgList) n);
        else if (n instanceof formalArg) endVisit((formalArg) n);
        else if (n instanceof typeDef) endVisit((typeDef) n);
        else if (n instanceof rulesSpecification) endVisit((rulesSpecification) n);
        else if (n instanceof ruleList) endVisit((ruleList) n);
        else if (n instanceof rule) endVisit((rule) n);
        else if (n instanceof analysisSpecification) endVisit((analysisSpecification) n);
        else if (n instanceof constraintsDef) endVisit((constraintsDef) n);
        else if (n instanceof estimatesDef) endVisit((estimatesDef) n);
        else if (n instanceof satisfyDef) endVisit((satisfyDef) n);
        else if (n instanceof localOrUpdateList) endVisit((localOrUpdateList) n);
        else if (n instanceof local) endVisit((local) n);
        else if (n instanceof update) endVisit((update) n);
        else if (n instanceof variableDeclaration) endVisit((variableDeclaration) n);
        else if (n instanceof identifier) endVisit((identifier) n);
        else if (n instanceof tupleLiteral) endVisit((tupleLiteral) n);
        else if (n instanceof setLiteral) endVisit((setLiteral) n);
        else if (n instanceof mapLiteral) endVisit((mapLiteral) n);
        else if (n instanceof mappingList) endVisit((mappingList) n);
        else if (n instanceof mapping) endVisit((mapping) n);
        else if (n instanceof relationLiteral) endVisit((relationLiteral) n);
        else if (n instanceof tupleLiteralList) endVisit((tupleLiteralList) n);
        else if (n instanceof mapLookup) endVisit((mapLookup) n);
        else if (n instanceof tupleSlot) endVisit((tupleSlot) n);
        else if (n instanceof algebraicTypeValue) endVisit((algebraicTypeValue) n);
        else if (n instanceof optArgList) endVisit((optArgList) n);
        else if (n instanceof functionCall) endVisit((functionCall) n);
        else if (n instanceof valueList) endVisit((valueList) n);
        else if (n instanceof caseExpr) endVisit((caseExpr) n);
        else if (n instanceof caseSpecList) endVisit((caseSpecList) n);
        else if (n instanceof unionExpr) endVisit((unionExpr) n);
        else if (n instanceof intersectExpr) endVisit((intersectExpr) n);
        else if (n instanceof relationSpecifier) endVisit((relationSpecifier) n);
        else if (n instanceof tupleSpecifier) endVisit((tupleSpecifier) n);
        else if (n instanceof setSpecifier) endVisit((setSpecifier) n);
        else if (n instanceof refSpecifier) endVisit((refSpecifier) n);
        else if (n instanceof qualifiedIdentifier) endVisit((qualifiedIdentifier) n);
        else if (n instanceof typeSpecifierList) endVisit((typeSpecifierList) n);
        else if (n instanceof typeName0) endVisit((typeName0) n);
        else if (n instanceof typeName1) endVisit((typeName1) n);
        else if (n instanceof typeName2) endVisit((typeName2) n);
        else if (n instanceof typeName3) endVisit((typeName3) n);
        else if (n instanceof value0) endVisit((value0) n);
        else if (n instanceof value1) endVisit((value1) n);
        else if (n instanceof value2) endVisit((value2) n);
        else if (n instanceof booleanValue0) endVisit((booleanValue0) n);
        else if (n instanceof booleanValue1) endVisit((booleanValue1) n);
        else if (n instanceof caseSpec0) endVisit((caseSpec0) n);
        else if (n instanceof caseSpec1) endVisit((caseSpec1) n);
        else if (n instanceof relationClosure0) endVisit((relationClosure0) n);
        else if (n instanceof relationClosure1) endVisit((relationClosure1) n);
        else if (n instanceof scalarType0) endVisit((scalarType0) n);
        else if (n instanceof scalarType1) endVisit((scalarType1) n);
        else if (n instanceof scalarType2) endVisit((scalarType2) n);
        else if (n instanceof scalarType3) endVisit((scalarType3) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
}

