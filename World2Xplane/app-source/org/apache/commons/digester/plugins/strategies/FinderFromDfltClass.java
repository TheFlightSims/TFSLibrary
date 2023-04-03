/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.PluginException;
/*    */ import org.apache.commons.digester.plugins.RuleFinder;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ 
/*    */ public class FinderFromDfltClass extends RuleFinder {
/* 35 */   public static String DFLT_RULECLASS_SUFFIX = "RuleInfo";
/*    */   
/* 36 */   public static String DFLT_METHOD_NAME = "addRules";
/*    */   
/*    */   private String rulesClassSuffix;
/*    */   
/*    */   private String methodName;
/*    */   
/*    */   public FinderFromDfltClass() {
/* 43 */     this(DFLT_RULECLASS_SUFFIX, DFLT_METHOD_NAME);
/*    */   }
/*    */   
/*    */   public FinderFromDfltClass(String rulesClassSuffix, String methodName) {
/* 55 */     this.rulesClassSuffix = rulesClassSuffix;
/* 56 */     this.methodName = methodName;
/*    */   }
/*    */   
/*    */   public RuleLoader findLoader(Digester digester, Class pluginClass, Properties p) throws PluginException {
/* 75 */     String rulesClassName = pluginClass.getName() + this.rulesClassSuffix;
/* 77 */     Class rulesClass = null;
/*    */     try {
/* 79 */       rulesClass = digester.getClassLoader().loadClass(rulesClassName);
/* 80 */     } catch (ClassNotFoundException cnfe) {}
/* 84 */     if (rulesClass == null)
/* 86 */       return null; 
/* 89 */     if (this.methodName == null)
/* 90 */       this.methodName = DFLT_METHOD_NAME; 
/* 93 */     return new LoaderFromClass(rulesClass, this.methodName);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromDfltClass.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */