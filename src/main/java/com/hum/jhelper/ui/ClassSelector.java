package com.hum.jhelper.ui;

import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;

/**
 * @author hum
 */
public class ClassSelector extends EditorTextField {
  public ClassSelector(String text, Project project) {
    // todo not work for java class input
    super(EditorFactory.getInstance().createDocument(text), project, StdFileTypes.JAVA);
  }

}