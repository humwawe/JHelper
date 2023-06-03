package com.hum.jhelper.actions;

import com.hum.jhelper.config.GlobalCache;
import com.hum.jhelper.config.JHelperProperties;
import com.hum.jhelper.ui.JHelperPropertiesDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class EditJHelperPropertiesAction extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    final Project project = e.getProject();
    JHelperProperties data = JHelperProperties.load(project);
    JHelperProperties result = JHelperPropertiesDialog.edit(project, data);
    if (result != null) {
      result.save(project);
      GlobalCache.addJHelperProperties(project, result);
    }
  }
}
