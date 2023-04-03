/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.PluginException;
/*    */ import org.apache.commons.digester.plugins.RuleFinder;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ 
/*    */ public class FinderFromDfltResource extends RuleFinder {
/* 40 */   public static String DFLT_RESOURCE_SUFFIX = "RuleInfo.xml";
/*    */   
/*    */   private String resourceSuffix;
/*    */   
/*    */   public FinderFromDfltResource() {
/* 46 */     this(DFLT_RESOURCE_SUFFIX);
/*    */   }
/*    */   
/*    */   public FinderFromDfltResource(String resourceSuffix) {
/* 57 */     this.resourceSuffix = resourceSuffix;
/*    */   }
/*    */   
/*    */   public RuleLoader findLoader(Digester d, Class pluginClass, Properties p) throws PluginException {
/* 74 */     String resourceName = pluginClass.getName().replace('.', '/') + this.resourceSuffix;
/* 78 */     InputStream is = pluginClass.getClassLoader().getResourceAsStream(resourceName);
/* 82 */     if (is == null)
/* 84 */       return null; 
/* 87 */     return FinderFromResource.loadRules(d, pluginClass, is, resourceName);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromDfltResource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */