/*    */ package org.apache.commons.digester.plugins.strategies;
/*    */ 
/*    */ import java.io.FileInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ import org.apache.commons.digester.Digester;
/*    */ import org.apache.commons.digester.plugins.PluginException;
/*    */ import org.apache.commons.digester.plugins.RuleFinder;
/*    */ import org.apache.commons.digester.plugins.RuleLoader;
/*    */ 
/*    */ public class FinderFromFile extends RuleFinder {
/* 45 */   public static String DFLT_FILENAME_ATTR = "file";
/*    */   
/*    */   private String filenameAttr;
/*    */   
/*    */   public FinderFromFile() {
/* 52 */     this(DFLT_FILENAME_ATTR);
/*    */   }
/*    */   
/*    */   public FinderFromFile(String filenameAttr) {
/* 57 */     this.filenameAttr = filenameAttr;
/*    */   }
/*    */   
/*    */   public RuleLoader findLoader(Digester d, Class pluginClass, Properties p) throws PluginException {
/* 73 */     String rulesFileName = p.getProperty(this.filenameAttr);
/* 74 */     if (rulesFileName == null)
/* 77 */       return null; 
/* 80 */     InputStream is = null;
/*    */     try {
/* 82 */       is = new FileInputStream(rulesFileName);
/* 83 */     } catch (IOException ioe) {
/* 84 */       throw new PluginException("Unable to process file [" + rulesFileName + "]", ioe);
/*    */     } 
/*    */     try {
/* 89 */       RuleLoader loader = new LoaderFromStream(is);
/* 90 */       return loader;
/* 91 */     } catch (Exception e) {
/* 92 */       throw new PluginException("Unable to load xmlrules from file [" + rulesFileName + "]", e);
/*    */     } finally {
/*    */       try {
/* 97 */         is.close();
/* 98 */       } catch (IOException ioe) {
/* 99 */         throw new PluginException("Unable to close stream for file [" + rulesFileName + "]", ioe);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\plugins\strategies\FinderFromFile.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */