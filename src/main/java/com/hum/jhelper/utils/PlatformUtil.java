package com.hum.jhelper.utils;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author hum
 */
public class PlatformUtil {
  public static Project getProject(DataContext dataContext) {
    return PlatformDataKeys.PROJECT.getData(dataContext);
  }

  public static VirtualFile getVFBaseDir(Project project) {
    return ProjectUtil.guessProjectDir(project);
  }
}
