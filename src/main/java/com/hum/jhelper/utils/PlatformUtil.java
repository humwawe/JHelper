package com.hum.jhelper.utils;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;

/**
 * @author hum
 */
public class PlatformUtil {
  public static Project getProject(DataContext dataContext) {
    return PlatformDataKeys.PROJECT.getData(dataContext);
  }
}
