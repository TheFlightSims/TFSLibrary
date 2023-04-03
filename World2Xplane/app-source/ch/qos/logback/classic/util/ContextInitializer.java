/*     */ package ch.qos.logback.classic.util;
/*     */ 
/*     */ import ch.qos.logback.classic.BasicConfigurator;
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.gaffer.GafferUtil;
/*     */ import ch.qos.logback.classic.joran.JoranConfigurator;
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.joran.spi.JoranException;
/*     */ import ch.qos.logback.core.status.ErrorStatus;
/*     */ import ch.qos.logback.core.status.InfoStatus;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.status.StatusManager;
/*     */ import ch.qos.logback.core.status.WarnStatus;
/*     */ import ch.qos.logback.core.util.Loader;
/*     */ import ch.qos.logback.core.util.OptionHelper;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ContextInitializer {
/*     */   public static final String GROOVY_AUTOCONFIG_FILE = "logback.groovy";
/*     */   
/*     */   public static final String AUTOCONFIG_FILE = "logback.xml";
/*     */   
/*     */   public static final String TEST_AUTOCONFIG_FILE = "logback-test.xml";
/*     */   
/*     */   public static final String CONFIG_FILE_PROPERTY = "logback.configurationFile";
/*     */   
/*     */   public static final String STATUS_LISTENER_CLASS = "logback.statusListenerClass";
/*     */   
/*     */   public static final String SYSOUT = "SYSOUT";
/*     */   
/*     */   final LoggerContext loggerContext;
/*     */   
/*     */   public ContextInitializer(LoggerContext loggerContext) {
/*  54 */     this.loggerContext = loggerContext;
/*     */   }
/*     */   
/*     */   public void configureByResource(URL url) throws JoranException {
/*  58 */     if (url == null)
/*  59 */       throw new IllegalArgumentException("URL argument cannot be null"); 
/*  61 */     if (url.toString().endsWith("groovy"))
/*  62 */       if (EnvUtil.isGroovyAvailable()) {
/*  65 */         GafferUtil.runGafferConfiguratorOn(this.loggerContext, this, url);
/*     */       } else {
/*  67 */         StatusManager sm = this.loggerContext.getStatusManager();
/*  68 */         sm.add((Status)new ErrorStatus("Groovy classes are not available on the class path. ABORTING INITIALIZATION.", this.loggerContext));
/*     */       }  
/*  72 */     if (url.toString().endsWith("xml")) {
/*  73 */       JoranConfigurator configurator = new JoranConfigurator();
/*  74 */       configurator.setContext((Context)this.loggerContext);
/*  75 */       configurator.doConfigure(url);
/*     */     } 
/*     */   }
/*     */   
/*     */   void joranConfigureByResource(URL url) throws JoranException {
/*  80 */     JoranConfigurator configurator = new JoranConfigurator();
/*  81 */     configurator.setContext((Context)this.loggerContext);
/*  82 */     configurator.doConfigure(url);
/*     */   }
/*     */   
/*     */   private URL findConfigFileURLFromSystemProperties(ClassLoader classLoader, boolean updateStatus) {
/*  86 */     String logbackConfigFile = OptionHelper.getSystemProperty("logback.configurationFile");
/*  87 */     if (logbackConfigFile != null) {
/*  88 */       URL result = null;
/*     */       try {
/*  90 */         result = new URL(logbackConfigFile);
/*  91 */         return result;
/*  92 */       } catch (MalformedURLException e) {
/*  95 */         result = Loader.getResource(logbackConfigFile, classLoader);
/*  96 */         if (result != null)
/*  97 */           return result; 
/*  99 */         File f = new File(logbackConfigFile);
/* 100 */         if (f.exists() && f.isFile())
/*     */           try {
/* 102 */             result = f.toURI().toURL();
/* 103 */             return result;
/* 104 */           } catch (MalformedURLException e1) {} 
/*     */       } finally {
/* 108 */         if (updateStatus)
/* 109 */           statusOnResourceSearch(logbackConfigFile, classLoader, result); 
/*     */       } 
/*     */     } 
/* 113 */     return null;
/*     */   }
/*     */   
/*     */   public URL findURLOfDefaultConfigurationFile(boolean updateStatus) {
/* 117 */     ClassLoader myClassLoader = Loader.getClassLoaderOfObject(this);
/* 118 */     URL url = findConfigFileURLFromSystemProperties(myClassLoader, updateStatus);
/* 119 */     if (url != null)
/* 120 */       return url; 
/* 123 */     url = getResource("logback.groovy", myClassLoader, updateStatus);
/* 124 */     if (url != null)
/* 125 */       return url; 
/* 128 */     url = getResource("logback-test.xml", myClassLoader, updateStatus);
/* 129 */     if (url != null)
/* 130 */       return url; 
/* 133 */     return getResource("logback.xml", myClassLoader, updateStatus);
/*     */   }
/*     */   
/*     */   private URL getResource(String filename, ClassLoader myClassLoader, boolean updateStatus) {
/* 137 */     URL url = Loader.getResource(filename, myClassLoader);
/* 138 */     if (updateStatus)
/* 139 */       statusOnResourceSearch(filename, myClassLoader, url); 
/* 141 */     return url;
/*     */   }
/*     */   
/*     */   public void autoConfig() throws JoranException {
/* 145 */     StatusListenerConfigHelper.installIfAsked(this.loggerContext);
/* 146 */     URL url = findURLOfDefaultConfigurationFile(true);
/* 147 */     if (url != null) {
/* 148 */       configureByResource(url);
/*     */     } else {
/* 150 */       BasicConfigurator.configure(this.loggerContext);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void multiplicityWarning(String resourceName, ClassLoader classLoader) {
/* 155 */     Set<URL> urlSet = null;
/* 156 */     StatusManager sm = this.loggerContext.getStatusManager();
/*     */     try {
/* 158 */       urlSet = Loader.getResourceOccurrenceCount(resourceName, classLoader);
/* 159 */     } catch (IOException e) {
/* 160 */       sm.add((Status)new ErrorStatus("Failed to get url list for resource [" + resourceName + "]", this.loggerContext, e));
/*     */     } 
/* 163 */     if (urlSet != null && urlSet.size() > 1) {
/* 164 */       sm.add((Status)new WarnStatus("Resource [" + resourceName + "] occurs multiple times on the classpath.", this.loggerContext));
/* 166 */       for (URL url : urlSet)
/* 167 */         sm.add((Status)new WarnStatus("Resource [" + resourceName + "] occurs at [" + url.toString() + "]", this.loggerContext)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void statusOnResourceSearch(String resourceName, ClassLoader classLoader, URL url) {
/* 174 */     StatusManager sm = this.loggerContext.getStatusManager();
/* 175 */     if (url == null) {
/* 176 */       sm.add((Status)new InfoStatus("Could NOT find resource [" + resourceName + "]", this.loggerContext));
/*     */     } else {
/* 179 */       sm.add((Status)new InfoStatus("Found resource [" + resourceName + "] at [" + url.toString() + "]", this.loggerContext));
/* 181 */       multiplicityWarning(resourceName, classLoader);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classi\\util\ContextInitializer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */