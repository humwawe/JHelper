package com.hum.jhelper.ui;

import com.hum.jhelper.config.JHelperProperties;
import com.hum.jhelper.utils.WindowUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.VerticalFlowLayout;

import javax.swing.*;
import java.awt.*;

/**
 * @author hum
 */
public class JHelperPropertiesDialog extends JDialog {
  private JHelperProperties data;
  private boolean isOk = false;
  private final DirectorySelector defaultDirectory;
  private final DirectorySelector outputDirectory;
  private final ClassSelector inputClass;
  private final ClassSelector outputClass;
  private final JTextField author;
  private final int width = new JTextField(25).getPreferredSize().width;

  public JHelperPropertiesDialog(Project project, JHelperProperties data) {
    super(null, "Project settings", Dialog.ModalityType.APPLICATION_MODAL);
    setAlwaysOnTop(true);
    setResizable(false);
    this.data = data;
    defaultDirectory = new DirectorySelector(project, data.defaultDirectory);
    outputDirectory = new DirectorySelector(project, data.outputDirectory);
    inputClass = new ClassSelector(data.inputClass, project);
    outputClass = new ClassSelector(data.outputClass, project);
    author = new JTextField(data.author);
    OkCancelPanel main = new OkCancelPanel(new VerticalFlowLayout()) {
      @Override
      public void onOk() {
        onChange();
        isOk = true;
        JHelperPropertiesDialog.this.setVisible(false);
      }

      @Override
      public void onCancel() {
        JHelperPropertiesDialog.this.data = null;
        JHelperPropertiesDialog.this.setVisible(false);
      }

      @Override
      public Dimension getPreferredSize() {
        Dimension dimension = super.getPreferredSize();
        dimension.width = width;
        return dimension;
      }
    };
    JPanel okCancelPanel = new JPanel(new GridLayout(1, 2));
    okCancelPanel.add(main.getOkButton());
    okCancelPanel.add(main.getCancelButton());
    main.add(new JLabel("Default directory:"));
    main.add(defaultDirectory);
    main.add(new JLabel("Output directory:"));
    main.add(outputDirectory);
    main.add(new JLabel("Input class:"));
    main.add(inputClass);
    main.add(new JLabel("Output class:"));
    main.add(outputClass);
    main.add(new JLabel("Author:"));
    main.add(author);
    main.add(okCancelPanel);
    setContentPane(main);
    onChange();
    pack();
    Point center = WindowUtil.getLocation(project, main.getSize());
    setLocation(center);
  }

  private void onChange() {
    data = new JHelperProperties(inputClass.getText(), outputClass.getText(), outputDirectory.getText(), author.getText(), defaultDirectory.getText());
  }

  @Override
  public void setVisible(boolean b) {
    if (b) {
      author.requestFocusInWindow();
      author.setSelectionStart(0);
      author.setSelectionEnd(author.getText().length());
    } else if (!isOk) {
      data = null;
    }
    super.setVisible(b);
  }

  public static JHelperProperties edit(Project project, JHelperProperties data) {
    JHelperPropertiesDialog dialog = new JHelperPropertiesDialog(project, data == null ? JHelperProperties.DEFAULT : data);
    dialog.setVisible(true);
    return dialog.data;
  }
}
