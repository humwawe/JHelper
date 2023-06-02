package com.hum.jhelper.utils;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

/**
 * @author hum
 */
public class FileUtil {
  public static String createFileWithContent(String fileName, String... path) {
    String basPath = String.join(File.separator, path);
    File base = new File(basPath);
    base.mkdirs();
    File file = new File(base, fileName);
    return "";

  }

  public static String createFileWithContent(String path, String fileName, String content) {
    return "";
  }

  public static boolean isChild(VirtualFile parent, VirtualFile child) {
    if (parent == null) {
      return false;
    }
    if (!parent.isDirectory()) {
      return false;
    }
    String parentPath = parent.getPath();
    if (!parentPath.endsWith("/")) {
      parentPath += "/";
    }
    String childPath = child.getPath();
    if (child.isDirectory() && !childPath.endsWith("/")) {
      childPath += "/";
    }
    return childPath.startsWith(parentPath);
  }

  public static String getRelativePath(VirtualFile baseDir, VirtualFile file) {
    if (file == null) {
      return null;
    }
    if (baseDir == null) {
      return file.getPath();
    }
    if (!isChild(baseDir, file)) {
      return null;
    }
    String basePath = baseDir.getPath();
    if (!basePath.endsWith("/")) {
      basePath += "/";
    }
    String filePath = file.getPath();
    return filePath.substring(Math.min(filePath.length(), basePath.length()));
  }

  public static VirtualFile getVFBaseDir(Project project) {
    return ProjectUtil.guessProjectDir(project);
  }
}
