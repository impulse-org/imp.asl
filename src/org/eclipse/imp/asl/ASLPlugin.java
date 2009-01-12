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

package org.eclipse.imp.asl;

import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ASLPlugin extends PluginBase {
    public static final String kPluginID= "org.eclipse.imp.asl";

    public static final String kLanguageName= "asl";

    /**
     * The unique instance of this plugin class
     */
    protected static ASLPlugin sPlugin;

    public static ASLPlugin getInstance() {
        if (sPlugin == null)
            new ASLPlugin();
        return sPlugin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        sPlugin= null;
        super.stop(context);
    }

    public String getID() {
        return kPluginID;
    }

    @Override
    public String getLanguageID() {
        return kLanguageName;
    }
}
