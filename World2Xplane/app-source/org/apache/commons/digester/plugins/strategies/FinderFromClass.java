/*     */ package org.apache.commons.digester.plugins.strategies;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.plugins.PluginException;
/*     */ import org.apache.commons.digester.plugins.RuleFinder;
/*     */ import org.apache.commons.digester.plugins.RuleLoader;
/*     */ 
/*     */ public class FinderFromClass extends RuleFinder {
/*  35 */   public static String DFLT_RULECLASS_ATTR = "ruleclass";
/*     */   
/*  36 */   public static String DFLT_METHOD_ATTR = "method";
/*     */   
/*  37 */   public static String DFLT_METHOD_NAME = "addRules";
/*     */   
/*     */   private String ruleClassAttr;
/*     */   
/*     */   private String methodAttr;
/*     */   
/*     */   private String dfltMethodName;
/*     */   
/*     */   public FinderFromClass() {
/*  47 */     this(DFLT_RULECLASS_ATTR, DFLT_METHOD_ATTR, DFLT_METHOD_NAME);
/*     */   }
/*     */   
/*     */   public FinderFromClass(String ruleClassAttr, String methodAttr, String dfltMethodName) {
/*  61 */     this.ruleClassAttr = ruleClassAttr;
/*  62 */     this.methodAttr = methodAttr;
/*  63 */     this.dfltMethodName = dfltMethodName;
/*     */   }
/*     */   
/*     */   public RuleLoader findLoader(Digester digester, Class pluginClass, Properties p) throws PluginException {
/*     */     Class ruleClass;
/*  96 */     String ruleClassName = p.getProperty(this.ruleClassAttr);
/*  97 */     if (ruleClassName == null)
/* 100 */       return null; 
/* 104 */     String methodName = null;
/* 105 */     if (this.methodAttr != null)
/* 106 */       methodName = p.getProperty(this.methodAttr); 
/* 108 */     if (methodName == null)
/* 109 */       methodName = this.dfltMethodName; 
/* 111 */     if (methodName == null)
/* 112 */       methodName = DFLT_METHOD_NAME; 
/*     */     try {
/* 118 */       ruleClass = digester.getClassLoader().loadClass(ruleClassName);
/* 120 */     } catch (ClassNotFoundException cnfe) {
/* 121 */       throw new PluginException("Unable to load class " + ruleClassName, cnfe);
/*     */     } 
/* 125 */     return new LoaderFromClass(ruleClass, methodName);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromClass.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */