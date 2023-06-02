package com.hum.jhelper.config;

import com.hum.jhelper.utils.PlatformUtil;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hum
 */
public class GlobalCache {
  private static final Map<Project, JHelperProperties> eligibleProjects = new HashMap<>();

  public static boolean isEligible(DataContext dataContext) {
    return eligibleProjects.containsKey(PlatformUtil.getProject(dataContext));
  }

  public static boolean isEligible(Project project) {
    return eligibleProjects.containsKey(project);
  }


  public static JHelperProperties getProperties(Project project) {
    return eligibleProjects.get(project);
  }

  public static void addJHelperProperties(Project project, JHelperProperties data) {
    eligibleProjects.put(project, data);
  }
}
