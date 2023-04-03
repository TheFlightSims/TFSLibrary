/*     */ package ch.qos.logback.classic.selector;
/*     */ 
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.joran.JoranConfigurator;
/*     */ import ch.qos.logback.classic.util.ContextInitializer;
/*     */ import ch.qos.logback.classic.util.JNDIUtil;
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.joran.spi.JoranException;
/*     */ import ch.qos.logback.core.status.InfoStatus;
/*     */ import ch.qos.logback.core.status.Status;
/*     */ import ch.qos.logback.core.status.StatusManager;
/*     */ import ch.qos.logback.core.status.StatusUtil;
/*     */ import ch.qos.logback.core.status.WarnStatus;
/*     */ import ch.qos.logback.core.util.Loader;
/*     */ import ch.qos.logback.core.util.StatusPrinter;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.NamingException;
/*     */ 
/*     */ public class ContextJNDISelector implements ContextSelector {
/*     */   private final Map<String, LoggerContext> synchronizedContextMap;
/*     */   
/*     */   private final LoggerContext defaultContext;
/*     */   
/*  57 */   private static final ThreadLocal<LoggerContext> threadLocal = new ThreadLocal<LoggerContext>();
/*     */   
/*     */   public ContextJNDISelector(LoggerContext context) {
/*  60 */     this.synchronizedContextMap = Collections.synchronizedMap(new HashMap<String, LoggerContext>());
/*  62 */     this.defaultContext = context;
/*     */   }
/*     */   
/*     */   public LoggerContext getDefaultLoggerContext() {
/*  66 */     return this.defaultContext;
/*     */   }
/*     */   
/*     */   public LoggerContext detachLoggerContext(String loggerContextName) {
/*  70 */     return this.synchronizedContextMap.remove(loggerContextName);
/*     */   }
/*     */   
/*     */   public LoggerContext getLoggerContext() {
/*  74 */     String contextName = null;
/*  75 */     Context ctx = null;
/*  78 */     LoggerContext lc = threadLocal.get();
/*  79 */     if (lc != null)
/*  80 */       return lc; 
/*     */     try {
/*  86 */       ctx = JNDIUtil.getInitialContext();
/*  87 */       contextName = JNDIUtil.lookup(ctx, "java:comp/env/logback/context-name");
/*  88 */     } catch (NamingException ne) {}
/*  92 */     if (contextName == null)
/*  94 */       return this.defaultContext; 
/*  97 */     LoggerContext loggerContext = this.synchronizedContextMap.get(contextName);
/*  99 */     if (loggerContext == null) {
/* 101 */       loggerContext = new LoggerContext();
/* 102 */       loggerContext.setName(contextName);
/* 103 */       this.synchronizedContextMap.put(contextName, loggerContext);
/* 104 */       URL url = findConfigFileURL(ctx, loggerContext);
/* 105 */       if (url != null) {
/* 106 */         configureLoggerContextByURL(loggerContext, url);
/*     */       } else {
/*     */         try {
/* 109 */           (new ContextInitializer(loggerContext)).autoConfig();
/* 110 */         } catch (JoranException je) {}
/*     */       } 
/* 114 */       if (!StatusUtil.contextHasStatusListener((Context)loggerContext))
/* 115 */         StatusPrinter.printInCaseOfErrorsOrWarnings((Context)loggerContext); 
/*     */     } 
/* 117 */     return loggerContext;
/*     */   }
/*     */   
/*     */   private String conventionalConfigFileName(String contextName) {
/* 122 */     return "logback-" + contextName + ".xml";
/*     */   }
/*     */   
/*     */   private URL findConfigFileURL(Context ctx, LoggerContext loggerContext) {
/* 126 */     StatusManager sm = loggerContext.getStatusManager();
/* 128 */     String jndiEntryForConfigResource = JNDIUtil.lookup(ctx, "java:comp/env/logback/configuration-resource");
/* 131 */     if (jndiEntryForConfigResource != null) {
/* 132 */       sm.add((Status)new InfoStatus("Searching for [" + jndiEntryForConfigResource + "]", this));
/* 134 */       URL url = urlByResourceName(sm, jndiEntryForConfigResource);
/* 135 */       if (url == null) {
/* 136 */         String msg = "The jndi resource [" + jndiEntryForConfigResource + "] for context [" + loggerContext.getName() + "] does not lead to a valid file";
/* 139 */         sm.add((Status)new WarnStatus(msg, this));
/*     */       } 
/* 141 */       return url;
/*     */     } 
/* 143 */     String resourceByConvention = conventionalConfigFileName(loggerContext.getName());
/* 145 */     return urlByResourceName(sm, resourceByConvention);
/*     */   }
/*     */   
/*     */   private URL urlByResourceName(StatusManager sm, String resourceName) {
/* 150 */     sm.add((Status)new InfoStatus("Searching for [" + resourceName + "]", this));
/* 152 */     URL url = Loader.getResource(resourceName, Loader.getTCL());
/* 153 */     if (url != null)
/* 154 */       return url; 
/* 156 */     return Loader.getResourceBySelfClassLoader(resourceName);
/*     */   }
/*     */   
/*     */   private void configureLoggerContextByURL(LoggerContext context, URL url) {
/*     */     try {
/* 161 */       JoranConfigurator configurator = new JoranConfigurator();
/* 162 */       context.reset();
/* 163 */       configurator.setContext((Context)context);
/* 164 */       configurator.doConfigure(url);
/* 165 */     } catch (JoranException e) {}
/* 167 */     StatusPrinter.printInCaseOfErrorsOrWarnings((Context)context);
/*     */   }
/*     */   
/*     */   public List<String> getContextNames() {
/* 171 */     List<String> list = new ArrayList<String>();
/* 172 */     list.addAll(this.synchronizedContextMap.keySet());
/* 173 */     return list;
/*     */   }
/*     */   
/*     */   public LoggerContext getLoggerContext(String name) {
/* 177 */     return this.synchronizedContextMap.get(name);
/*     */   }
/*     */   
/*     */   public int getCount() {
/* 186 */     return this.synchronizedContextMap.size();
/*     */   }
/*     */   
/*     */   public void setLocalContext(LoggerContext context) {
/* 198 */     threadLocal.set(context);
/*     */   }
/*     */   
/*     */   public void removeLocalContext() {
/* 202 */     threadLocal.remove();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\selector\ContextJNDISelector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */