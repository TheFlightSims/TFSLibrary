/*     */ package org.apache.commons.digester.plugins;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class Declaration {
/*     */   private Class pluginClass;
/*     */   
/*     */   private String pluginClassName;
/*     */   
/*     */   private String id;
/*     */   
/*  44 */   private Properties properties = new Properties();
/*     */   
/*     */   private boolean initialized = false;
/*     */   
/*  53 */   private RuleLoader ruleLoader = null;
/*     */   
/*     */   public Declaration(String pluginClassName) {
/*  65 */     this.pluginClassName = pluginClassName;
/*     */   }
/*     */   
/*     */   public Declaration(Class pluginClass) {
/*  72 */     this.pluginClass = pluginClass;
/*  73 */     this.pluginClassName = pluginClass.getName();
/*     */   }
/*     */   
/*     */   public Declaration(Class pluginClass, RuleLoader ruleLoader) {
/*  82 */     this.pluginClass = pluginClass;
/*  83 */     this.pluginClassName = pluginClass.getName();
/*  84 */     this.ruleLoader = ruleLoader;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  97 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 107 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setProperties(Properties p) {
/* 124 */     this.properties.putAll(p);
/*     */   }
/*     */   
/*     */   public Class getPluginClass() {
/* 133 */     return this.pluginClass;
/*     */   }
/*     */   
/*     */   public void init(Digester digester, PluginManager pm) throws PluginException {
/* 143 */     Log log = digester.getLogger();
/* 144 */     boolean debug = log.isDebugEnabled();
/* 145 */     if (debug)
/* 146 */       log.debug("init being called!"); 
/* 149 */     if (this.initialized)
/* 150 */       throw new PluginAssertionFailure("Init called multiple times."); 
/* 153 */     if (this.pluginClass == null && this.pluginClassName != null)
/*     */       try {
/* 156 */         this.pluginClass = digester.getClassLoader().loadClass(this.pluginClassName);
/* 158 */       } catch (ClassNotFoundException cnfe) {
/* 159 */         throw new PluginException("Unable to load class " + this.pluginClassName, cnfe);
/*     */       }  
/* 164 */     if (this.ruleLoader == null) {
/* 167 */       log.debug("Searching for ruleloader...");
/* 168 */       this.ruleLoader = pm.findLoader(digester, this.id, this.pluginClass, this.properties);
/*     */     } else {
/* 170 */       log.debug("This declaration has an explicit ruleLoader.");
/*     */     } 
/* 173 */     if (debug)
/* 174 */       if (this.ruleLoader == null) {
/* 175 */         log.debug("No ruleLoader found for plugin declaration id [" + this.id + "]" + ", class [" + this.pluginClass.getClass().getName() + "].");
/*     */       } else {
/* 180 */         log.debug("RuleLoader of type [" + this.ruleLoader.getClass().getName() + "] associated with plugin declaration" + " id [" + this.id + "]" + ", class [" + this.pluginClass.getClass().getName() + "].");
/*     */       }  
/* 188 */     this.initialized = true;
/*     */   }
/*     */   
/*     */   public void configure(Digester digester, String pattern) throws PluginException {
/* 202 */     Log log = digester.getLogger();
/* 203 */     boolean debug = log.isDebugEnabled();
/* 204 */     if (debug)
/* 205 */       log.debug("configure being called!"); 
/* 208 */     if (!this.initialized)
/* 209 */       throw new PluginAssertionFailure("Not initialized."); 
/* 212 */     if (this.ruleLoader != null)
/* 213 */       this.ruleLoader.addRules(digester, pattern); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\Declaration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */