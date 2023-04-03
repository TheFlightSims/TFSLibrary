/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.PluginException;
/*    */ import org.apache.commons.digester.plugins.RuleFinder;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ 
/*    */ public class FinderFromMethod extends RuleFinder {
/* 39 */   public static String DFLT_METHOD_ATTR = "method";
/*    */   
/*    */   private String methodAttr;
/*    */   
/*    */   public FinderFromMethod() {
/* 46 */     this(DFLT_METHOD_ATTR);
/*    */   }
/*    */   
/*    */   public FinderFromMethod(String methodAttr) {
/* 51 */     this.methodAttr = methodAttr;
/*    */   }
/*    */   
/*    */   public RuleLoader findLoader(Digester d, Class pluginClass, Properties p) throws PluginException {
/* 69 */     String methodName = p.getProperty(this.methodAttr);
/* 70 */     if (methodName == null)
/* 73 */       return null; 
/* 76 */     return new LoaderFromClass(pluginClass, methodName);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromMethod.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */