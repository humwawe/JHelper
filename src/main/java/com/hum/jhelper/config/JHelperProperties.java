package com.hum.jhelper.config;

import com.hum.jhelper.constants.Constants;
import com.hum.jhelper.utils.PlatformUtil;
import com.hum.jhelper.utils.RunnableUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.*;
import java.util.Properties;

/**
 * @author hum
 */
public class JHelperProperties {
  public static final JHelperProperties DEFAULT = new JHelperProperties("java.util.Scanner", "java.io.PrintWriter", "output", "hum", "src/main/java");

  public final String inputClass;
  public final String outputClass;
  public final String outputDirectory;
  public final String author;
  public final String defaultDirectory;

  public JHelperProperties(String inputClass, String outputClass, String outputDirectory, String author, String defaultDirectory) {
    this.inputClass = inputClass.trim();
    this.outputClass = outputClass.trim();
    this.outputDirectory = outputDirectory.trim();
    this.author = author.trim();
    this.defaultDirectory = defaultDirectory.trim();
  }

  public JHelperProperties(Properties properties) {
    inputClass = properties.getProperty(Constants.INPUT_CLASS, DEFAULT.inputClass);
    outputClass = properties.getProperty(Constants.OUTPUT_CLASS, DEFAULT.outputClass);
    outputDirectory = properties.getProperty(Constants.OUTPUT_DIRECTORY, DEFAULT.outputDirectory);
    author = properties.getProperty(Constants.AUTHOR, DEFAULT.author);
    defaultDirectory = properties.getProperty(Constants.DEFAULT_DIRECTORY, DEFAULT.defaultDirectory);
  }

  public static JHelperProperties load(Project project) {
    if (project == null) {
      return null;
    }
    String basePath = project.getBasePath();
    File folder = new File(basePath, Constants.JHELPER);
    if (folder.exists() && folder.isDirectory()) {
      Properties properties = loadProperties(folder);
      if (properties == null) {
        return null;
      }
      return new JHelperProperties(properties);
    }
    return null;

  }

  private static Properties loadProperties(File folder) {
    try (InputStream input = new FileInputStream(new File(folder, Constants.JHELPER_PROPERTIES))) {
      Properties prop = new Properties();
      prop.load(input);
      return prop;
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public void save(Project project) {
    RunnableUtil.run(() -> {
      if (project == null) {
        return;
      }
      VirtualFile root = PlatformUtil.getVFBaseDir(project);
      if (root == null) {
        return;
      }
      try {
        VirtualFile jHelperPath = root.findChild(Constants.JHELPER);
        if (jHelperPath == null) {
          jHelperPath = root.createChildDirectory(null, Constants.JHELPER);
        }
        VirtualFile config = jHelperPath.findChild(Constants.JHELPER_PROPERTIES);
        if (config == null) {
          config = jHelperPath.createChildData(null, Constants.JHELPER_PROPERTIES);
        }
        Properties properties = new Properties();
        properties.setProperty(Constants.INPUT_CLASS, inputClass);
        properties.setProperty(Constants.OUTPUT_CLASS, outputClass);
        properties.setProperty(Constants.OUTPUT_DIRECTORY, outputDirectory);
        properties.setProperty(Constants.AUTHOR, author);
        properties.setProperty(Constants.DEFAULT_DIRECTORY, defaultDirectory);
        OutputStream outputStream = config.getOutputStream(null);
        properties.store(outputStream, "");
        outputStream.close();
      } catch (IOException e) {
        throw new RuntimeException(" save project: " + e);
      }
    });
  }


}
