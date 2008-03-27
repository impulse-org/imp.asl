package org.eclipse.imp.asl.builder;

import org.eclipse.core.resources.IProject;
import org.eclipse.imp.asl.ASLPlugin;
import org.eclipse.imp.builder.ProjectNatureBase;
import org.eclipse.imp.runtime.IPluginLog;
import org.eclipse.imp.smapifier.builder.SmapiProjectNature;

public class ASLNature extends ProjectNatureBase {
    // SMS 28 Mar 2007:  plugin class now totally parameterized
    public static final String k_natureID= ASLPlugin.kPluginID + ".asl.nature";

    public String getNatureID() {
        return k_natureID;
    }

    public String getBuilderID() {
        return ASLBuilder.BUILDER_ID;
    }

    public void addToProject(IProject project) {
        super.addToProject(project);
        new SmapiProjectNature("ASL").addToProject(project);
    };

    protected void refreshPrefs() {
        // TODO implement preferences and hook in here
    }

    public IPluginLog getLog() {
        // SMS 28 Mar 2007:  plugin class now totally parameterized
        return ASLPlugin.getInstance();
    }

    protected String getDownstreamBuilderID() {
        // TODO Auto-generated method stub
        return null;
    }
}
