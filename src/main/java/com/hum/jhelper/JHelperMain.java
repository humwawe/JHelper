package com.hum.jhelper;

import com.hum.jhelper.config.GlobalCache;
import com.hum.jhelper.config.JHelperProperties;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;

/**
 * @author hum
 */
public class JHelperMain implements ProjectManagerListener {

  public JHelperMain(Project project) {
    System.out.println("START");
    JHelperProperties jHelperProperties = JHelperProperties.load(project);
    GlobalCache.addJHelperProperties(project, jHelperProperties);
  }

}
