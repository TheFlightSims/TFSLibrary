/*     */ package org.apache.commons.configuration;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.SystemUtils;
/*     */ import org.apache.tools.ant.taskdefs.Execute;
/*     */ 
/*     */ public class EnvironmentConfiguration extends AbstractConfiguration {
/*     */   private static final String METHOD_NAME = "getenv";
/*     */   
/*     */   private static final int VERSION_1_5 = 150;
/*     */   
/*     */   private Map environment;
/*     */   
/*     */   public EnvironmentConfiguration() {
/*  71 */     if (SystemUtils.isJavaVersionAtLeast(150)) {
/*  73 */       extractProperties15();
/*     */     } else {
/*  77 */       extractProperties14();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addPropertyDirect(String key, Object value) {
/*  90 */     throw new UnsupportedOperationException("Configuration is read-only!");
/*     */   }
/*     */   
/*     */   public boolean containsKey(String key) {
/* 100 */     return this.environment.containsKey(key);
/*     */   }
/*     */   
/*     */   public Iterator getKeys() {
/* 110 */     return this.environment.keySet().iterator();
/*     */   }
/*     */   
/*     */   public Object getProperty(String key) {
/* 120 */     return this.environment.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 130 */     return this.environment.isEmpty();
/*     */   }
/*     */   
/*     */   public void clearProperty(String key) {
/* 141 */     throw new UnsupportedOperationException("Configuration is read-only!");
/*     */   }
/*     */   
/*     */   public void clear() {
/* 151 */     throw new UnsupportedOperationException("Configuration is read-only!");
/*     */   }
/*     */   
/*     */   void extractProperties14() {
/* 160 */     extractPropertiesFromCollection(Execute.getProcEnvironment());
/*     */   }
/*     */   
/*     */   void extractPropertiesFromCollection(Collection env) {
/* 172 */     this.environment = new HashMap();
/* 173 */     for (Iterator it = env.iterator(); it.hasNext(); ) {
/* 175 */       String entry = it.next();
/* 176 */       int pos = entry.indexOf('=');
/* 177 */       if (pos == -1) {
/* 179 */         getLogger().warn("Ignoring: " + entry);
/*     */         continue;
/*     */       } 
/* 183 */       this.environment.put(entry.substring(0, pos), entry.substring(pos + 1));
/*     */     } 
/*     */   }
/*     */   
/*     */   void extractProperties15() {
/*     */     try {
/* 199 */       Method method = System.class.getMethod("getenv", null);
/* 200 */       this.environment = (Map)method.invoke(null, null);
/* 202 */     } catch (Exception ex) {
/* 205 */       throw new ConfigurationRuntimeException("Error when accessing environment properties", ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\EnvironmentConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */