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

package org.eclipse.imp.asl.compiler;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.asl.ASLPlugin;
import org.eclipse.imp.asl.parser.ASLParseController;
import org.eclipse.imp.asl.parser.ast.ASTNode;
import org.eclipse.imp.asl.parser.ast.AbstractVisitor;
import org.eclipse.imp.asl.parser.ast.ItypeName;
import org.eclipse.imp.asl.parser.ast.algebraicTypeDeclaration;
import org.eclipse.imp.asl.parser.ast.ctorDeclaration;
import org.eclipse.imp.asl.parser.ast.formalArg;
import org.eclipse.imp.asl.parser.ast.formalArgList;
import org.eclipse.imp.asl.parser.ast.identifier;
import org.eclipse.imp.asl.parser.ast.optFormalArgList;
import org.eclipse.imp.asl.parser.ast.typeName0;
import org.eclipse.imp.asl.parser.ast.typeName1;
import org.eclipse.imp.asl.parser.ast.typeName2;
import org.eclipse.imp.asl.parser.ast.typeName3;
import org.eclipse.imp.builder.BuilderUtils;
import org.eclipse.imp.builder.MarkerCreator;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

public class ASLCompiler {
    public static class Pair<T1,T2> {
        public final T1 fFirst;
        public final T2 fSecond;

        public Pair(T1 v1, T2 v2) {
            fFirst= v1;
            fSecond= v2;
        }
    }

    public class Constructor {
        private final Type fOwningType;
        private List<Pair<String,String>> fArguments= new ArrayList<Pair<String,String>>();

        public Constructor(Type newType) {
            fOwningType= newType;
            fOwningType.addConstructor(this);
        }

        public void addArgument(String type, String name) {
            fArguments.add(new Pair<String,String>(type, name));
        }

        public List<Pair<String,String>> getArguments() {
            return fArguments;
        }

        public void generate(PrintWriter pw) {
            pw.print("    public " + fOwningType.fName + "(");
            int idx= 0;
            for(Pair<String,String> arg: fArguments) {
                if (idx > 0) { pw.print(", "); }
                pw.print(arg.fFirst);
                pw.print(' ');
                pw.print(arg.fSecond);
            }
            pw.println(") {");
            for(Pair<String,String> arg: fArguments) {
                pw.println("        this." + arg.fSecond + " = " + arg.fSecond + ";");
            }
            pw.println("    }");
        }

        public Set<String> getReferencedTypes() {
            Set<String> result= new HashSet<String>();
            for(Pair<String,String> arg: fArguments) {
                result.add(arg.fFirst);
            }
            return result;
        }
    }

    public class Type {
        private final String fPackage;
        private final String fName;
        private final List<Constructor> fConstructors= new ArrayList<Constructor>();
        private final List<String> fImports= new ArrayList<String>();
        private final List<Pair<String,String>> fFields= new ArrayList<Pair<String,String>>();

        public Type(String typeName, String pkg) {
            fName= typeName;
            fPackage= pkg;
        }

        public void addConstructor(Constructor ctor) {
            fConstructors.add(ctor);
        }

        private void collectFields() {
            for(Constructor ctor: fConstructors) {
                fFields.addAll(ctor.getArguments());
            }
            Collections.sort(fImports);
        }

        private void collectTypes() {
            for(Constructor ctor: fConstructors) {
                fImports.addAll(ctor.getReferencedTypes());
            }
            Collections.sort(fImports);
        }

        public void generate(IProgressMonitor mon) {
            final String fileName= fName + ".java";
            IFile newFile= fFile.getProject().getFile(fFile.getProjectRelativePath().removeLastSegments(1).append(fileName));
            StringWriter sw= new StringWriter(256);

            collectTypes();
            collectFields();
            generate(new PrintWriter(sw));

            String typeSource= sw.toString();
            ByteArrayInputStream bais= new ByteArrayInputStream(typeSource.getBytes());

            try {
                if (!newFile.exists()) {
                    newFile.create(bais, true, mon);
                } else {
                    newFile.setContents(bais, true, false, mon);
                }
            } catch (CoreException ce) {
                System.err.println(ce.getMessage());
            }
        }

        public void generate(PrintWriter pw) {
            if (fPackage != null && fPackage.length() > 0) {
                pw.println("package " + fPackage + ";");
            }
            pw.println();
            if (!fImports.isEmpty()) {
                for(String imp: fImports) {
                    pw.println("import " + imp + ";");
                }
                pw.println();
            }
            pw.println("public class " + fName + " {");
            for(Pair<String,String> field: fFields) {
                pw.println("    " + field.fFirst + " " + field.fSecond + ";");
            }
            for(Constructor ctor: fConstructors) {
                ctor.generate(pw);
            }
            pw.println("}");
        }
    }

    public String PROBLEM_MARKER_ID;

    private IFile fFile;

    private String fPackage;

    public ASLCompiler(String problem_marker_id) {
        PROBLEM_MARKER_ID= problem_marker_id;
    }

    private final class TranslatorVisitor extends AbstractVisitor {
        private String fParentType;
        private final IProgressMonitor fMonitor;

        public TranslatorVisitor(IFile file, IProgressMonitor mon) {
            fFile= file;
            fMonitor= mon;
        }

        public void unimplementedVisitor(String s) {
            // System.err.println("Don't know how to translate node type '" + s + "'.");
        }

        @Override
        public boolean visit(algebraicTypeDeclaration n) {
            fParentType= n.getidentifier().getIDENTIFIER().toString();
            return true;
        }

        @Override
        public boolean visit(ctorDeclaration n) {
            String typeName= n.getidentifier().getIDENTIFIER().toString();
            Type newType= new Type(typeName, fPackage);
            Constructor ctor= new Constructor(newType);
            final optFormalArgList formalArgList= n.getoptFormalArgList();
            if (formalArgList != null) {
                formalArgList argList= formalArgList.getformalArgList();
                for(int i=0; i < argList.size(); i++) {
                    formalArg arg= argList.getformalArgAt(i);
                    final String argType= getNameForType(arg.gettype());
                    String name= arg.getname().getIDENTIFIER().toString();
                    ctor.addArgument(argType, name);
                }
            }
            newType.generate(fMonitor);
            return true;
        }

        private String getNameForType(ItypeName type) {
            if (type instanceof typeName0) {
                return "int";
            } else if (type instanceof typeName1) {
                return "double";
            } else if (type instanceof typeName2) {
                return "boolean";
            } else if (type instanceof typeName3) {
                return "String";
            }
            return ((identifier) type).toString();
        }
    }

    public ASLCompiler() {
        super();
    }

    public String getFileContents(IFile file) {
        try {
            return BuilderUtils.getFileContents(file);
        } catch (Exception e) {
            System.err.println("ASLCompiler.getFileContents(..):  " + e.getMessage());
        }
        return "";
    }

    private String getPackageFor(IFile file) throws JavaModelException {
        IJavaProject javaProject= JavaCore.create(file.getProject());
        IPath srcFolderRelPath= findSrcFolderRelativePath(file, javaProject);
        return srcFolderRelPath.toPortableString().replace('/', '.');
    }

    private IPath findSrcFolderRelativePath(IFile file, IJavaProject javaProject) throws JavaModelException {
        final IPath folderPath= file.getFullPath().removeLastSegments(1);
        IClasspathEntry[] entries= javaProject.getResolvedClasspath(true);

        for(IClasspathEntry entry: entries) {
            if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
                final IPath entryPath= entry.getPath();
                if (entryPath.isPrefixOf(folderPath)) {
                    int segCount= entryPath.matchingFirstSegments(folderPath);
                    return folderPath.removeFirstSegments(segCount);
                }
            }
        }
        return null;
    }

    public void compile(IFile file, IProgressMonitor mon) {
        if (file == null) {
            System.err.println("ASLCompiler.compile(..):  File is null; returning without parsing");
            return;
        }
        IProject project= file.getProject();
        if (project == null) {
            System.err.println("ASLCompiler.compile(..):  Project is null; returning without parsing");
            return;
        }
        ISourceProject sourceProject= null;
        try {
            sourceProject= ModelFactory.open(project);
        } catch (ModelException me) {
            System.err.println("ASLCompiler.compile(..):  Model exception:\n" + me.getMessage() + "\nReturning without parsing");
            return;
        }
        try {
            fPackage= getPackageFor(file);
        } catch (JavaModelException e) {
            ASLPlugin.getInstance().logException("Exception encountered determining package containing ASL source file.", e);
            return;
        }
        IParseController parseController= new ASLParseController();
        // Marker creator handles error messages from the parse controller
        MarkerCreator markerCreator= new MarkerCreator(file, PROBLEM_MARKER_ID);
        // If we have a kind of parser that might be receptive, tell it
        // what types of problem marker the builder will create
        parseController.getAnnotationTypeInfo().addProblemMarkerType(PROBLEM_MARKER_ID);
        parseController.initialize(file.getProjectRelativePath(), sourceProject, markerCreator);
        parseController.parse(getFileContents(file), mon);
        ASTNode currentAst= (ASTNode) parseController.getCurrentAst();
        if (currentAst == null) {
            System.err.println("ASLCompiler.compile(..):  current AST is null (parse errors?); unable to compile.");
            return;
        }
        currentAst.accept(new TranslatorVisitor(file, mon));
    }
}
