package com.hum.jhelper.config;

import com.hum.jhelper.constants.Constants;
import com.intellij.openapi.project.Project;

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
    inputClass = properties.getProperty("inputClass", DEFAULT.inputClass);
    outputClass = properties.getProperty("outputClass", DEFAULT.outputClass);
    outputDirectory = properties.getProperty("outputDirectory", DEFAULT.outputDirectory);
    author = properties.getProperty("author", DEFAULT.author);
    defaultDirectory = properties.getProperty("defaultDirectory", DEFAULT.defaultDirectory);
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
    // todo
    //    ApplicationManager.getApplication().runWriteAction(new Runnable() {
    //      public void run() {
    //        if (project == null) {
    //          return;
    //        }
    //        VirtualFile root = project.getBaseDir();
    //        if (root == null) {
    //          return;
    //        }
    //        try {
    //          VirtualFile config = root.findOrCreateChildData(null, "chelper.properties");
    //          Properties properties = new Properties();
    //          properties.setProperty("inputClass", inputClass);
    //          properties.setProperty("outputClass", outputClass);
    //          properties.setProperty("outputDirectory", outputDirectory);
    //          properties.setProperty("author", author);
    //          properties.setProperty("defaultDirectory", defaultDirectory);
    //          OutputStream outputStream = config.getOutputStream(null);
    //          properties.store(outputStream, "");
    //          outputStream.close();
    //        } catch (IOException e) {
    //          throw new RuntimeException(e);
    //        }
    //      }
    //    });
  }


}
