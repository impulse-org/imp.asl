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

package org.eclipse.imp.asl.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.BuilderUtils;
import org.eclipse.imp.builder.MarkerCreator;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.imp.asl.ASLPlugin;
import org.eclipse.imp.asl.compiler.ASLCompiler;
import org.eclipse.imp.asl.parser.ASLParseController;

/**
 * @author
 */
public class ASLBuilder extends BuilderBase {
    /**
     * Extension ID of the Anspec builder. Must match the ID in the corresponding
     * extension definition in plugin.xml.
     * SMS 22 Mar 2007:  If that ID is set through the NewBuilder wizard, then so must this one be.
     */
    // SMS 28 Mar 2007:  Make plugin class name totally parameterized
    public static final String BUILDER_ID= ASLPlugin.kPluginID + ".asl.builder";

    // SMS 28 Mar 2007:  Make problem id parameterized (rather than just ".problem") so that
    // it can be given a builde-specific value (not simply composed here using the builder id
    // because the problem id is also needed in ExtensionPointEnabler for adding the marker
    // extension to the plugin.xml file)
    public static final String PROBLEM_MARKER_ID= ASLPlugin.kPluginID + ".asl.builder.problem";

    // SMS 11 May 2006
    public static final String LANGUAGE_NAME= "asl";

    public static final Language LANGUAGE= LanguageRegistry.findLanguage(LANGUAGE_NAME);

    protected PluginBase getPlugin() {
        //return AnspecPlugin.getInstance();
        return ASLPlugin.getInstance();
    }

    protected String getErrorMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected String getWarningMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected String getInfoMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    // SMS 11 May 2006
    // Incorporated realisitic handling of filename extensions
    // using information recorded in the language registry
    protected boolean isSourceFile(IFile file) {
        IPath path= file.getRawLocation();
        if (path == null)
            return false;
        String pathString= path.toString();
        if (pathString.indexOf("/bin/") != -1)
            return false;
        return LANGUAGE.hasExtension(path.getFileExtension());
    }

    /**
     * @return true iff the given file is a source file that this builder should scan
     * for dependencies, but not compile as a top-level compilation unit.<br>
     * <code>isNonRootSourceFile()</code> and <code>isSourceFile()</code> should never
     * return true for the same file.
     */
    protected boolean isNonRootSourceFile(IFile resource) {
        // TODO:  If your language has non-root source files (e.g., header files), then
        // reimplement this method to test for those
        System.err.println("ASLBuilder.isNonRootSourceFile(..) returning FALSE by default");
        return false;
    }

    /**
     * Collects compilation-unit dependencies for the given file, and records
     * them via calls to <code>fDependency.addDependency()</code>.
     */
    protected void collectDependencies(IFile file) {
        // TODO:  If your langauge has inter-file dependencies then reimplement
        // this method to collect those
        System.err.println("ASLBuilder.collectDependencies(..) doing nothing by default");
        return;
    }

    protected boolean isOutputFolder(IResource resource) {
        return resource.getFullPath().lastSegment().equals("bin");
    }

    protected void compile(final IFile file, IProgressMonitor monitor) {
        try {
            // START_HERE
            ASLCompiler compiler= new ASLCompiler(PROBLEM_MARKER_ID);

            compiler.compile(file, monitor);
            doRefresh(file.getParent());
        } catch (Exception e) {
            getPlugin().writeErrorMsg(e.getMessage());
            e.printStackTrace();
        }
    }

    protected void runParserForCompiler(final IFile file, IProgressMonitor monitor) {
        try {
            // Parse controller is the "compiler" here; parses and reports errors
            IParseController parseController= new ASLParseController();
            // Marker creator handles error messages from the parse controller (and
            // uses the parse controller to get additional information about the errors)
            MarkerCreator markerCreator= new MarkerCreator(file, PROBLEM_MARKER_ID);
            // If we have a kind of parser that might be receptive, tell it
            // what types of problem marker the builder will create
            parseController.getAnnotationTypeInfo().addProblemMarkerType(getErrorMarkerID());
            // Need to tell the parse controller which file in which project to parse
            // and also the message handler to which to report errors
            IProject project= file.getProject();
            ISourceProject sourceProject= null;
            try {
                sourceProject= ModelFactory.open(project);
            } catch (ModelException me) {
                System.err.println("AnspecParseController.runParserForComplier(..):  Model exception:\n" + me.getMessage() + "\nReturning without parsing");
                return;
            }
            parseController.initialize(file.getProjectRelativePath(), sourceProject, markerCreator);
            String contents= BuilderUtils.getFileContents(file);
            // Finally parse it
            parseController.parse(contents, monitor);
            doRefresh(file.getParent());
        } catch (Exception e) {
            getPlugin().writeErrorMsg(e.getMessage());
            e.printStackTrace();
        }
    }
}
