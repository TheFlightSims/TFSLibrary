/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.RuleFinder;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ 
/*    */ public class FinderSetProperties extends RuleFinder {
/* 46 */   public static String DFLT_PROPS_ATTR = "setprops";
/*    */   
/* 47 */   public static String DFLT_FALSEVAL = "false";
/*    */   
/*    */   private String propsAttr;
/*    */   
/*    */   private String falseval;
/*    */   
/*    */   public FinderSetProperties() {
/* 54 */     this(DFLT_PROPS_ATTR, DFLT_FALSEVAL);
/*    */   }
/*    */   
/*    */   public FinderSetProperties(String propsAttr, String falseval) {
/* 69 */     this.propsAttr = propsAttr;
/* 70 */     this.falseval = falseval;
/*    */   }
/*    */   
/*    */   public RuleLoader findLoader(Digester d, Class pluginClass, Properties p) {
/* 88 */     String state = p.getProperty(this.propsAttr);
/* 89 */     if (state != null && state.equals(this.falseval))
/* 92 */       return null; 
/* 95 */     return new LoaderSetProperties();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderSetProperties.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */