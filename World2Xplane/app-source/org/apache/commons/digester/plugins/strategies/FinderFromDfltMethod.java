/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.PluginException;
/*    */ import org.apache.commons.digester.plugins.RuleFinder;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ 
/*    */ public class FinderFromDfltMethod extends RuleFinder {
/* 37 */   public static String DFLT_METHOD_NAME = "addRules";
/*    */   
/*    */   private String methodName;
/*    */   
/*    */   public FinderFromDfltMethod() {
/* 43 */     this(DFLT_METHOD_NAME);
/*    */   }
/*    */   
/*    */   public FinderFromDfltMethod(String methodName) {
/* 54 */     this.methodName = methodName;
/*    */   }
/*    */   
/*    */   public RuleLoader findLoader(Digester d, Class pluginClass, Properties p) throws PluginException {
/* 72 */     Method rulesMethod = LoaderFromClass.locateMethod(pluginClass, this.methodName);
/* 73 */     if (rulesMethod == null)
/* 74 */       return null; 
/* 77 */     return new LoaderFromClass(pluginClass, rulesMethod);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromDfltMethod.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */