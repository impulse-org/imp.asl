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

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.ILexer;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IParser;
import org.eclipse.imp.parser.MessageHandlerAdapter;
import org.eclipse.imp.parser.SimpleLPGParseController;
import org.eclipse.imp.services.ILanguageSyntaxProperties;

/**
 * @author Stan Sutton (suttons@us.ibm.com) (for the following modifications)
 * @since May 1,  2007	Addition of marker types
 * @since May 10, 2007	Conversion IProject -> ISourceProject
 * @since May 31, 2007  Adapted to extend SimpleLPGParseController
 */
public class ASLParseController extends SimpleLPGParseController implements IParseController {
    private ASLParser parser;

    private ASLLexer lexer;

    /**
     * @param filePath		Project-relative path of file
     * @param project		Project that contains the file
     * @param handler		A message handler to receive error messages (or any others)
     * 						from the parser
     */
    private IPath fullFilePath;

    public ASLParseController() {
        lexer= new ASLLexer();
        parser= new ASLParser();
    }

    public void initialize(IPath filePath, ISourceProject project, IMessageHandler handler) {
        super.initialize(filePath, project, handler);
        fullFilePath= project.getRawProject().getLocation().append(filePath);
        // createLexerAndParser(fullFilePath);
    }

    public IParser getParser() {
        return parser;
    }

    public ILexer getLexer() {
        return lexer;
    }

    public ISourcePositionLocator getNodeLocator() {
        return new ASLSourcePositionLocator();
    }

    //    private void createLexerAndParser(IPath filePath) {
    //        try {
    //            lexer = new AnspecLexer(filePath.toOSString()); // Create the lexer
    //            parser = new AnspecParser(lexer.getLexStream() /*, project*/);  // Create the parser
    //        } catch (IOException e) {
    //            throw new Error(e);
    //        }
    //    }
    /**
     * setFilePath() should be called before calling this method.
     */
    public Object parse(String contents, boolean scanOnly, IProgressMonitor monitor) {
        PMMonitor my_monitor= new PMMonitor(monitor);
        char[] contentsArray= contents.toCharArray();
        lexer.reset(contentsArray, fullFilePath.toOSString()); // fFilePath.toPortableString() ?
        parser.reset(lexer.getLexStream());
        parser.getParseStream().setMessageHandler(new MessageHandlerAdapter(handler));
        // SMS 28 Mar 2007
        // Commenting out to prevent clobbering of markers set by previous
        // builders in the same build phase.  This will also give behavior
        // that is more consistent with the handling of markers in the JDT.
        //        IResource file = project.getFile(filePath);
        //        try {
        //            file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
        //        } catch(CoreException e) {
        //        System.err.println("anspecParseController.parse:  caught CoreException while deleting problem markers; continuing to parse regardless");
        //        }
        lexer.lexer(my_monitor, parser.getParseStream()); // Lex the stream to produce the token stream
        if (my_monitor.isCancelled())
            return fCurrentAst; // TODO fCurrentAst might (probably will) be inconsistent wrt the lex stream now
        fCurrentAst= parser.parser(my_monitor, 0);
//      parser.resolve((ASTNode) fCurrentAst);
        cacheKeywordsOnce();
        return fCurrentAst;
    }

    public ILanguageSyntaxProperties getSyntaxProperties() {
        return null;
    }
}
