package com.hum.jhelper.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author hum
 */
public class WindowUtil {
  public static Point getLocation(Project project, Dimension size) {
    JComponent component = Objects.requireNonNull(WindowManager.getInstance().getIdeFrame(project)).getComponent();
    Point center = component.getLocationOnScreen();
    center.x += component.getWidth() / 2;
    center.y += component.getHeight() / 2;
    center.x -= size.getWidth() / 2;
    center.y -= size.getHeight() / 2;
    return center;
  }
}
