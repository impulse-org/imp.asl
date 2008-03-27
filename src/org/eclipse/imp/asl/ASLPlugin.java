package org.eclipse.imp.asl;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.imp.preferences.PreferencesService;
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

    protected static PreferencesService preferencesService= null;

    public static PreferencesService getPreferencesService() {
        if (preferencesService == null) {
            preferencesService= new PreferencesService(ResourcesPlugin.getWorkspace().getRoot().getProject());
            preferencesService.setLanguageName(kLanguageName);
            // TODO:  When some actual preferences are created, put
            // a call to the preferences initializer here
            // (The IMP New Preferences Support wizard creates such
            // an initializer.)
        }
        return preferencesService;
    }
}
