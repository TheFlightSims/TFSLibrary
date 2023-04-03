package org.hsqldb.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class FrameworkLogger {
  private static Map loggerInstances = new HashMap<Object, Object>();
  
  private static Map jdkToLog4jLevels = new HashMap<Object, Object>();
  
  private static Method log4jGetLogger;
  
  private static Method log4jLogMethod;
  
  private Object log4jLogger;
  
  private Logger jdkLogger;
  
  private static boolean noopMode;
  
  public static synchronized String report() {
    return loggerInstances.size() + " logger instances:  " + loggerInstances.keySet();
  }
  
  public static synchronized void clearLoggers(String paramString) {
    HashSet<String> hashSet = new HashSet();
    Iterator<String> iterator = loggerInstances.keySet().iterator();
    String str = paramString + '.';
    while (iterator.hasNext()) {
      String str1 = iterator.next();
      if (str1.equals(paramString) || str1.startsWith(str))
        hashSet.add(str1); 
    } 
    loggerInstances.keySet().removeAll(hashSet);
  }
  
  static void reconfigure() {
    noopMode = false;
    Class<?> clazz = null;
    loggerInstances.clear();
    clazz = null;
    log4jGetLogger = null;
    log4jLogMethod = null;
    try {
      clazz = Class.forName("org.apache.log4j.Logger");
    } catch (Exception exception) {}
    if (clazz != null)
      try {
        if (jdkToLog4jLevels.size() < 1) {
          Method method = Class.forName("org.apache.log4j.Level").getMethod("toLevel", new Class[] { String.class });
          jdkToLog4jLevels.put(Level.ALL, method.invoke(null, new Object[] { "ALL" }));
          jdkToLog4jLevels.put(Level.FINER, method.invoke(null, new Object[] { "DEBUG" }));
          jdkToLog4jLevels.put(Level.WARNING, method.invoke(null, new Object[] { "ERROR" }));
          jdkToLog4jLevels.put(Level.SEVERE, method.invoke(null, new Object[] { "FATAL" }));
          jdkToLog4jLevels.put(Level.INFO, method.invoke(null, new Object[] { "INFO" }));
          jdkToLog4jLevels.put(Level.OFF, method.invoke(null, new Object[] { "OFF" }));
          jdkToLog4jLevels.put(Level.FINEST, method.invoke(null, new Object[] { "TRACE" }));
          jdkToLog4jLevels.put(Level.WARNING, method.invoke(null, new Object[] { "WARN" }));
        } 
        log4jLogMethod = clazz.getMethod("log", new Class[] { String.class, Class.forName("org.apache.log4j.Priority"), Object.class, Throwable.class });
        log4jGetLogger = clazz.getMethod("getLogger", new Class[] { String.class });
        return;
      } catch (Exception exception) {
        try {
          System.err.println("<clinit> failure instantiating configured Log4j system: " + exception);
        } catch (Throwable throwable) {}
      }  
    clazz = null;
    log4jLogMethod = null;
    log4jGetLogger = null;
    String str = System.getProperty("hsqldb.reconfig_logging");
    if (str != null && str.equalsIgnoreCase("false"))
      return; 
    try {
      LogManager logManager = LogManager.getLogManager();
      String str1 = "/org/hsqldb/resources/jdklogging-default.properties";
      if (isDefaultJdkConfig()) {
        logManager.reset();
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new BasicTextJdkLogFormatter(false));
        consoleHandler.setLevel(Level.INFO);
        logManager.readConfiguration(FrameworkLogger.class.getResourceAsStream(str1));
        Logger logger = Logger.getLogger("org.hsqldb.cmdline");
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
      } else {
        logManager.readConfiguration();
      } 
    } catch (Exception exception) {
      noopMode = true;
      System.err.println("<clinit> failure initializing JDK logging system.  Continuing without Application logging.");
      exception.printStackTrace();
    } 
  }
  
  private FrameworkLogger(String paramString) {
    if (!noopMode)
      if (log4jGetLogger == null) {
        this.jdkLogger = Logger.getLogger(paramString);
      } else {
        try {
          this.log4jLogger = log4jGetLogger.invoke(null, new Object[] { paramString });
        } catch (Exception exception) {
          throw new RuntimeException("Failed to instantiate Log4j Logger", exception);
        } 
      }  
    synchronized (FrameworkLogger.class) {
      loggerInstances.put(paramString, this);
    } 
  }
  
  public static FrameworkLogger getLog(Class paramClass) {
    return getLog(paramClass.getName());
  }
  
  public static FrameworkLogger getLog(Class paramClass, String paramString) {
    return (paramString == null) ? getLog(paramClass) : getLog(paramString + '.' + paramClass.getName());
  }
  
  public static FrameworkLogger getLog(String paramString1, String paramString2) {
    return (paramString2 == null) ? getLog(paramString1) : getLog(paramString2 + '.' + paramString1);
  }
  
  public static synchronized FrameworkLogger getLog(String paramString) {
    return loggerInstances.containsKey(paramString) ? (FrameworkLogger)loggerInstances.get(paramString) : new FrameworkLogger(paramString);
  }
  
  public void log(Level paramLevel, String paramString, Throwable paramThrowable) {
    privlog(paramLevel, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void privlog(Level paramLevel, String paramString, Throwable paramThrowable, int paramInt, Class paramClass) {
    if (noopMode)
      return; 
    if (this.log4jLogger == null) {
      StackTraceElement[] arrayOfStackTraceElement = (new Throwable()).getStackTrace();
      String str1 = arrayOfStackTraceElement[paramInt].getClassName();
      String str2 = arrayOfStackTraceElement[paramInt].getMethodName();
      if (paramThrowable == null) {
        this.jdkLogger.logp(paramLevel, str1, str2, paramString);
      } else {
        this.jdkLogger.logp(paramLevel, str1, str2, paramString, paramThrowable);
      } 
    } else {
      try {
        log4jLogMethod.invoke(this.log4jLogger, new Object[] { paramClass.getName(), jdkToLog4jLevels.get(paramLevel), paramString, paramThrowable });
      } catch (Exception exception) {
        throw new RuntimeException("Logging failed when attempting to log: " + paramString, exception);
      } 
    } 
  }
  
  public void enduserlog(Level paramLevel, String paramString) {
    if (noopMode)
      return; 
    if (this.log4jLogger == null) {
      String str1 = FrameworkLogger.class.getName();
      String str2 = "\\l";
      this.jdkLogger.logp(paramLevel, str1, str2, paramString);
    } else {
      try {
        log4jLogMethod.invoke(this.log4jLogger, new Object[] { FrameworkLogger.class.getName(), jdkToLog4jLevels.get(paramLevel), paramString, null });
      } catch (Exception exception) {
        throw new RuntimeException("Logging failed when attempting to log: " + paramString, exception);
      } 
    } 
  }
  
  public void log(Level paramLevel, String paramString) {
    privlog(paramLevel, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void finer(String paramString) {
    privlog(Level.FINER, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void warning(String paramString) {
    privlog(Level.WARNING, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void severe(String paramString) {
    privlog(Level.SEVERE, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void info(String paramString) {
    privlog(Level.INFO, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void finest(String paramString) {
    privlog(Level.FINEST, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void error(String paramString) {
    privlog(Level.WARNING, paramString, null, 2, FrameworkLogger.class);
  }
  
  public void finer(String paramString, Throwable paramThrowable) {
    privlog(Level.FINER, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void warning(String paramString, Throwable paramThrowable) {
    privlog(Level.WARNING, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void severe(String paramString, Throwable paramThrowable) {
    privlog(Level.SEVERE, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void info(String paramString, Throwable paramThrowable) {
    privlog(Level.INFO, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void finest(String paramString, Throwable paramThrowable) {
    privlog(Level.FINEST, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public void error(String paramString, Throwable paramThrowable) {
    privlog(Level.WARNING, paramString, paramThrowable, 2, FrameworkLogger.class);
  }
  
  public static boolean isDefaultJdkConfig() {
    File file = new File(System.getProperty("java.home"), "lib/logging.properties");
    if (!file.isFile())
      return false; 
    FileInputStream fileInputStream = null;
    LogManager logManager = LogManager.getLogManager();
    try {
      fileInputStream = new FileInputStream(file);
      Properties properties = new Properties();
      properties.load(fileInputStream);
      Enumeration<?> enumeration = properties.propertyNames();
      byte b = 0;
      while (enumeration.hasMoreElements()) {
        b++;
        String str1 = (String)enumeration.nextElement();
        String str2 = logManager.getProperty(str1);
        if (str2 == null)
          return false; 
        if (!logManager.getProperty(str1).equals(str2))
          return false; 
      } 
      return true;
    } catch (IOException iOException) {
      return false;
    } finally {
      if (fileInputStream != null)
        try {
          fileInputStream.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  static {
    try {
      reconfigure();
    } catch (SecurityException securityException) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\FrameworkLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */