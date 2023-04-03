/*     */ package org.apache.commons.digester.plugins.strategies;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Properties;
/*     */ import org.apache.commons.digester.Digester;
/*     */ import org.apache.commons.digester.plugins.PluginException;
/*     */ import org.apache.commons.digester.plugins.RuleFinder;
/*     */ import org.apache.commons.digester.plugins.RuleLoader;
/*     */ 
/*     */ public class FinderFromResource extends RuleFinder {
/*  42 */   public static String DFLT_RESOURCE_ATTR = "resource";
/*     */   
/*     */   private String resourceAttr;
/*     */   
/*     */   public FinderFromResource() {
/*  49 */     this(DFLT_RESOURCE_ATTR);
/*     */   }
/*     */   
/*     */   public FinderFromResource(String resourceAttr) {
/*  54 */     this.resourceAttr = resourceAttr;
/*     */   }
/*     */   
/*     */   public RuleLoader findLoader(Digester d, Class pluginClass, Properties p) throws PluginException {
/*  70 */     String resourceName = p.getProperty(this.resourceAttr);
/*  71 */     if (resourceName == null)
/*  74 */       return null; 
/*  77 */     InputStream is = pluginClass.getClassLoader().getResourceAsStream(resourceName);
/*  81 */     if (is == null)
/*  82 */       throw new PluginException("Resource " + resourceName + " not found."); 
/*  86 */     return loadRules(d, pluginClass, is, resourceName);
/*     */   }
/*     */   
/*     */   public static RuleLoader loadRules(Digester d, Class pluginClass, InputStream is, String resourceName) throws PluginException {
/*     */     try {
/* 105 */       RuleLoader loader = new LoaderFromStream(is);
/* 106 */       return loader;
/* 107 */     } catch (Exception e) {
/* 108 */       throw new PluginException("Unable to load xmlrules from resource [" + resourceName + "]", e);
/*     */     } finally {
/*     */       try {
/* 113 */         is.close();
/* 114 */       } catch (IOException ioe) {
/* 115 */         throw new PluginException("Unable to close stream for resource [" + resourceName + "]", ioe);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromResource.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */