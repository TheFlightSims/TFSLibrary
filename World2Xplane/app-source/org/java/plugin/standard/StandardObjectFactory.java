/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.ObjectFactory;
/*     */ import org.java.plugin.PathResolver;
/*     */ import org.java.plugin.PluginManager;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ import org.java.plugin.util.ExtendedProperties;
/*     */ 
/*     */ public class StandardObjectFactory extends ObjectFactory {
/*     */   static final String PACKAGE_NAME = "org.java.plugin.standard";
/*     */   
/*  36 */   protected Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   protected ExtendedProperties config;
/*     */   
/*     */   protected void configure(ExtendedProperties configuration) {
/*  44 */     this.config = (configuration != null) ? configuration : new ExtendedProperties();
/*     */   }
/*     */   
/*     */   protected String getImplClassName(Class<?> cls) {
/*  49 */     String result = this.config.getProperty(cls.getName(), null);
/*  50 */     if (this.log.isDebugEnabled())
/*  51 */       this.log.debug("implementation class for " + cls.getName() + " is " + result); 
/*  54 */     return result;
/*     */   }
/*     */   
/*     */   protected Object createClassInstance(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
/*  60 */     ClassLoader cl = Thread.currentThread().getContextClassLoader();
/*  61 */     if (cl != null)
/*     */       try {
/*  63 */         return cl.loadClass(className).newInstance();
/*  64 */       } catch (ClassNotFoundException cnfe) {} 
/*  68 */     cl = getClass().getClassLoader();
/*  69 */     if (cl != null)
/*     */       try {
/*  71 */         return cl.loadClass(className).newInstance();
/*  72 */       } catch (ClassNotFoundException cnfe) {} 
/*  76 */     return ClassLoader.getSystemClassLoader().loadClass(className).newInstance();
/*     */   }
/*     */   
/*     */   public PluginRegistry createRegistry() {
/*     */     PluginRegistry result;
/*  85 */     String className = getImplClassName(PluginRegistry.class);
/*  87 */     if (className == null)
/*  88 */       className = "org.java.plugin.registry.xml.PluginRegistryImpl"; 
/*     */     try {
/*  91 */       result = (PluginRegistry)createClassInstance(className);
/*  92 */     } catch (Exception e) {
/*  93 */       this.log.fatal("failed creating registry instance " + className, e);
/*  95 */       throw new Error("failed creating registry instance " + className, e);
/*     */     } 
/*  98 */     result.configure(this.config.getSubset(className + "."));
/*  99 */     this.log.debug("registry instance created - " + result);
/* 100 */     return result;
/*     */   }
/*     */   
/*     */   public PathResolver createPathResolver() {
/*     */     PathResolver result;
/* 108 */     String className = getImplClassName(PathResolver.class);
/* 110 */     if (className == null)
/* 111 */       className = "org.java.plugin.standard.StandardPathResolver"; 
/*     */     try {
/* 114 */       result = (PathResolver)createClassInstance(className);
/* 115 */     } catch (Exception e) {
/* 116 */       this.log.fatal("failed creating path resolver instance " + className, e);
/* 118 */       throw new Error("failed creating path resolver instance " + className, e);
/*     */     } 
/*     */     try {
/* 122 */       result.configure(this.config.getSubset(className + "."));
/* 123 */     } catch (Exception e) {
/* 124 */       this.log.fatal("failed configuring path resolver instance " + result, e);
/* 126 */       throw new Error("failed configuring path resolver instance " + result, e);
/*     */     } 
/* 129 */     this.log.debug("path resolver instance created - " + result);
/* 130 */     return result;
/*     */   }
/*     */   
/*     */   protected PluginLifecycleHandler createLifecycleHandler() {
/*     */     PluginLifecycleHandler result;
/* 140 */     String className = getImplClassName(PluginLifecycleHandler.class);
/* 142 */     if (className == null)
/* 143 */       className = "org.java.plugin.standard.StandardPluginLifecycleHandler"; 
/*     */     try {
/* 147 */       result = (PluginLifecycleHandler)createClassInstance(className);
/* 148 */     } catch (Exception e) {
/* 149 */       this.log.fatal("failed creating plug-in life cycle handler instance " + className, e);
/* 151 */       throw new Error("failed creating plug-in life cycle handler instance " + className, e);
/*     */     } 
/* 155 */     result.configure(this.config.getSubset(className + "."));
/* 156 */     this.log.debug("life cycle handler instance created - " + result);
/* 157 */     return result;
/*     */   }
/*     */   
/*     */   public PluginManager createManager(PluginRegistry registry, PathResolver pathResolver) {
/* 168 */     return new StandardPluginManager(registry, pathResolver, createLifecycleHandler());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\StandardObjectFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */