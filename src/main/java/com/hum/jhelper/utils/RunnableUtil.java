package com.hum.jhelper.utils;

import com.intellij.openapi.application.ApplicationManager;

/**
 * @author hum
 */
public class RunnableUtil {
  public static void run(Runnable runnable) {
    ApplicationManager.getApplication().runWriteAction(runnable);
  }
}
