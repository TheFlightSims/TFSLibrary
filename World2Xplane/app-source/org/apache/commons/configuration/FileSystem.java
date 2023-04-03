/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.commons.logging.impl.NoOpLog;
/*     */ 
/*     */ public abstract class FileSystem {
/*     */   private static final String FILE_SYSTEM = "org.apache.commons.configuration.filesystem";
/*     */   
/*     */   private static FileSystem fileSystem;
/*     */   
/*     */   private Log log;
/*     */   
/*     */   private FileOptionsProvider optionsProvider;
/*     */   
/*     */   public FileSystem() {
/*  51 */     setLogger(null);
/*     */   }
/*     */   
/*     */   public Log getLogger() {
/*  61 */     return this.log;
/*     */   }
/*     */   
/*     */   public void setLogger(Log log) {
/*  75 */     this.log = (log != null) ? log : (Log)new NoOpLog();
/*     */   }
/*     */   
/*     */   static {
/*  80 */     String fsClassName = System.getProperty("org.apache.commons.configuration.filesystem");
/*  81 */     if (fsClassName != null) {
/*  83 */       Log log = LogFactory.getLog(FileSystem.class);
/*     */       try {
/*  87 */         Class clazz = Class.forName(fsClassName);
/*  88 */         if (FileSystem.class.isAssignableFrom(clazz)) {
/*  90 */           fileSystem = (FileSystem)clazz.newInstance();
/*  91 */           if (log.isDebugEnabled())
/*  93 */             log.debug("Using " + fsClassName); 
/*     */         } 
/*  97 */       } catch (InstantiationException ex) {
/*  99 */         log.error("Unable to create " + fsClassName, ex);
/* 101 */       } catch (IllegalAccessException ex) {
/* 103 */         log.error("Unable to create " + fsClassName, ex);
/* 105 */       } catch (ClassNotFoundException ex) {
/* 107 */         log.error("Unable to create " + fsClassName, ex);
/*     */       } 
/*     */     } 
/* 111 */     if (fileSystem == null)
/* 113 */       fileSystem = new DefaultFileSystem(); 
/*     */   }
/*     */   
/*     */   public static void setDefaultFileSystem(FileSystem fs) throws NullPointerException {
/* 124 */     if (fs == null)
/* 126 */       throw new NullPointerException("A FileSystem implementation is required"); 
/* 128 */     fileSystem = fs;
/*     */   }
/*     */   
/*     */   public static void resetDefaultFileSystem() {
/* 136 */     fileSystem = new DefaultFileSystem();
/*     */   }
/*     */   
/*     */   public static FileSystem getDefaultFileSystem() {
/* 145 */     return fileSystem;
/*     */   }
/*     */   
/*     */   public void setFileOptionsProvider(FileOptionsProvider provider) {
/* 154 */     this.optionsProvider = provider;
/*     */   }
/*     */   
/*     */   public FileOptionsProvider getFileOptionsProvider() {
/* 159 */     return this.optionsProvider;
/*     */   }
/*     */   
/*     */   public abstract InputStream getInputStream(String paramString1, String paramString2) throws ConfigurationException;
/*     */   
/*     */   public abstract InputStream getInputStream(URL paramURL) throws ConfigurationException;
/*     */   
/*     */   public abstract OutputStream getOutputStream(URL paramURL) throws ConfigurationException;
/*     */   
/*     */   public abstract OutputStream getOutputStream(File paramFile) throws ConfigurationException;
/*     */   
/*     */   public abstract String getPath(File paramFile, URL paramURL, String paramString1, String paramString2);
/*     */   
/*     */   public abstract String getBasePath(String paramString);
/*     */   
/*     */   public abstract String getFileName(String paramString);
/*     */   
/*     */   public abstract URL locateFromURL(String paramString1, String paramString2);
/*     */   
/*     */   public abstract URL getURL(String paramString1, String paramString2) throws MalformedURLException;
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\FileSystem.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */