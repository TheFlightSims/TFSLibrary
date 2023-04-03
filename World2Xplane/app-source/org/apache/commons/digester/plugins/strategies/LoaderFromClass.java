/*     */ package org.apache.commons.digester.plugins.strategies;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.commons.beanutils.MethodUtils;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.plugins.PluginException;
/*     */ import org.apache.commons.digester.plugins.RuleLoader;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class LoaderFromClass extends RuleLoader {
/*     */   private Class rulesClass;
/*     */   
/*     */   private Method rulesMethod;
/*     */   
/*     */   public LoaderFromClass(Class rulesClass, Method rulesMethod) {
/*  43 */     this.rulesClass = rulesClass;
/*  44 */     this.rulesMethod = rulesMethod;
/*     */   }
/*     */   
/*     */   public LoaderFromClass(Class rulesClass, String methodName) throws PluginException {
/*  51 */     Method method = locateMethod(rulesClass, methodName);
/*  53 */     if (method == null)
/*  54 */       throw new PluginException("rule class " + rulesClass.getName() + " does not have method " + methodName + " or that method has an invalid signature."); 
/*  60 */     this.rulesClass = rulesClass;
/*  61 */     this.rulesMethod = method;
/*     */   }
/*     */   
/*     */   public void addRules(Digester d, String path) throws PluginException {
/*  68 */     Log log = d.getLogger();
/*  69 */     boolean debug = log.isDebugEnabled();
/*  70 */     if (debug)
/*  71 */       log.debug("LoaderFromClass loading rules for plugin at path [" + path + "]"); 
/*     */     try {
/*  77 */       Object[] params = { d, path };
/*  78 */       this.rulesMethod.invoke(null, params);
/*  79 */     } catch (Exception e) {
/*  80 */       throw new PluginException("Unable to invoke rules method " + this.rulesMethod + " on rules class " + this.rulesClass, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Method locateMethod(Class rulesClass, String methodName) throws PluginException {
/*  96 */     Class[] paramSpec = { Digester.class, String.class };
/*  97 */     Method rulesMethod = MethodUtils.getAccessibleMethod(rulesClass, methodName, paramSpec);
/* 100 */     return rulesMethod;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\LoaderFromClass.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */